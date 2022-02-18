package top.zhx47.qxx.job;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.core.utils.StringUtils;
import top.zhx47.qxx.datasource.entity.SysConfig;
import top.zhx47.qxx.datasource.entity.SysNotice;
import top.zhx47.qxx.datasource.entity.SysSite;
import top.zhx47.qxx.datasource.po.SystemInfoPO;
import top.zhx47.qxx.service.PlatformInfoService;
import top.zhx47.qxx.service.SysConfigService;
import top.zhx47.qxx.service.SysNoticeService;
import top.zhx47.qxx.service.SysSiteService;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/26 20:39
 */
@Component
public class AutoUpdate {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoUpdate.class);

    @Value("${qdd.getUrl}")
    private String getUrl;
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private SysSiteService sysSiteService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private PlatformInfoService platformInfoService;
    @Autowired
    private SystemInfoPO systemInfoPO;

    /**
     * 写入文件
     *
     * @param filepath 文件路径
     * @param content  写入内容
     */
    private static void bufferedWriterFile(String filepath, String content) throws IOException {
        LOGGER.info("☞写入文件：{}", filepath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        }
    }

    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void autoUpdate() throws IOException {
        String qdd_url = this.systemInfoPO.getQddUrl();
        Document document = Jsoup.connect(getUrl)
                .get();
        String html = document.select("body > div.container-fluid.zw_txt > div.cont").html();
        String rgex = "xyz\\|(.*?)\\|xyz";
        Matcher m = Pattern.compile(rgex).matcher(html);
        while (m.find() && !m.group(1).equals(qdd_url)) {
            qdd_url = m.group(1);
            this.systemInfoPO.setQddUrl(qdd_url);
            this.platformInfoService.updateQDDURL(qdd_url);
        }
        String token = this.getQDDToken();
        if (StringUtils.isNotEmpty(token)) {
            LOGGER.info("☟☟☟开始更新数据库☟☟☟");
            this.updateNotice(qdd_url, token);
            this.updateSite(qdd_url, token);
            this.updateVersion(qdd_url, token);
            LOGGER.info("☝☝☝数据库更新成功☝☝☝");
        }
        if (StringUtils.isNotEmpty(this.systemInfoPO.getFilePath()) && StringUtils.isNotEmpty(this.systemInfoPO.getQxxApi())) {
            LOGGER.info("☟☟☟开始更新本地文件☟☟☟");
            this.updateFrontFile(qdd_url);
            LOGGER.info("☝☝☝本地文件更新成功☝☝☝");
        }
    }

    @Scheduled(cron = "0 0 0/12 * * ?")
    public void updateQXXCookie() throws IOException {
        String token = this.getQDDToken();
        if (StringUtils.isNotBlank(token)) {
            platformInfoService.updateQDDCookie(token);
        }
    }

    /**
     * 获取抢多多Token
     *
     * @return 抢多多Token
     */
    private String getQDDToken() throws IOException {
        String qddPhone = this.systemInfoPO.getQddPhone();
        String qddPassword = this.systemInfoPO.getQddPassword();
        String qddUrl = this.systemInfoPO.getQddUrl();
        if (StringUtils.isNotEmpty(qddPhone) && StringUtils.isNotEmpty(qddPassword) && StringUtils.isNotEmpty(qddUrl)) {
            Map<String, String> body = new HashMap<>();
            body.put("phone", qddPhone);
            body.put("password", qddPassword);
            Connection.Response execute = Jsoup.connect(qddUrl + "/common/login")
                    .data(body)
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .execute();
            return execute.cookie("token");
        }
        return null;
    }

    /**
     * 更新前端文件
     *
     * @param qdd_url 抢多多URL
     */
    private void updateFrontFile(String qdd_url) throws IOException {
        Connection.Response execute = Jsoup.connect(qdd_url + "/index.html?r=" + new Random().nextDouble()).method(Connection.Method.GET).execute();
        bufferedWriterFile(this.systemInfoPO.getFilePath() + "/index.html", execute.body());
        Document qddIndex = Jsoup.parse(execute.body());
        Elements link = qddIndex.getElementsByTag("link");
        for (Element element : link) {
            String href = element.attr("href");
            execute = Jsoup.connect(qdd_url + href).ignoreContentType(true).method(Connection.Method.GET).execute();
            String content = execute.body();
            if (href.contains("index") && href.startsWith("/js")) {
                content = content.replaceAll(qdd_url, this.systemInfoPO.getQxxApi())
                        .replaceAll("t\\.p\\+\"js/\"", "\"" + qdd_url + "/js/\"")
                        .replaceAll("\"css/\"", "\"/" + qdd_url.replace("https://", "") + "/css/\"")
                        .replaceAll("i\\.p\\+\"img/", "\"" + qdd_url + "/img/");
            }
            bufferedWriterFile(this.systemInfoPO.getFilePath() + href, content);
        }
    }

    /**
     * 更新版本信息
     *
     * @param qdd_url 抢多多URL
     * @param token   抢多多Token
     */
    private void updateVersion(String qdd_url, String token) throws IOException {
        LOGGER.info("☞更新表数据：sys_config");
        Connection.Response execute = Jsoup.connect(qdd_url + "/common/get_version")
                .header("token", token)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
        SysConfig sysConfig = JSONObject.parseObject(execute.body()).getJSONObject("body").getObject("config", SysConfig.class);
//        sysConfig.setId(1L);
//        sysConfig.setDomain("http://127.0.0.1");
//        sysConfig.setJiguang("Basic MjFkNTY3Y2U4NTFmNDUxMDIwNzRmZDRhOjQwOWMyN2RkMzQ1ZWQ3OGMzNmRjYjRmZQ==");
//        sysConfig.setVersionApk("2.2.40");
        LambdaUpdateWrapper<SysConfig> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysConfig::getVersion, sysConfig.getVersion())
                        .eq(SysConfig::getId, 1);
        sysConfigService.update(updateWrapper);
    }

    /**
     * 更新平台列表
     *
     * @param qdd_url 抢多多URL
     * @param token   抢多多Token
     */
    private void updateSite(String qdd_url, String token) throws IOException {
        LOGGER.info("☞更新表数据：sys_site");
        Connection.Response execute = Jsoup.connect(qdd_url + "/user/get_site_list")
                .header("token", token)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
        List<SysSite> sysSiteList = JSONObject.parseObject(execute.body()).getJSONObject("body").getObject("siteList", new TypeReference<List<SysSite>>() {
        });
        sysSiteService.saveOrUpdateBatch(sysSiteList);
    }

    /**
     * 更新公告
     *
     * @param qdd_url 抢多多URL
     * @param token   抢多多Token
     */
    private void updateNotice(String qdd_url, String token) throws IOException {
        LOGGER.info("☞更新表数据：sys_notice");
        Connection.Response execute = Jsoup.connect(qdd_url + "/common/get_notice")
                .header("token", token)
                .data("name", "login")
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
        SysNotice sysNotice = JSONObject.parseObject(execute.body()).getJSONObject("body").getObject("notice", SysNotice.class);
        sysNotice.setTitle(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "自动更新！！！");
        sysNotice.setId(1L);
        this.sysNoticeService.saveOrUpdate(sysNotice);
    }
}
