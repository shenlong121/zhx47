package top.zhx47.qxx.controller;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.utils.IdUtils;
import top.zhx47.common.core.web.R;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.api.controller.UserControllerApi;
import top.zhx47.qxx.api.datasource.dto.*;
import top.zhx47.qxx.datasource.entity.Collect;
import top.zhx47.qxx.datasource.entity.Site;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/16 23:11
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {
    @Autowired
    private UserService userService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private PlatformInfoService platformInfoService;
    @Autowired
    private PlatformInfoBakService platformInfoBakService;

    @Override
    public R getUser() throws Exception {
        User user = userService.getUser();
        return R.ok().putBodyByMap("user", user);
    }

    @Override
    public R getUserDetail() throws Exception {
        return this.getUser();
    }

    @Override
    public R getSiteList() throws Exception {
        List<Site> list = siteService.getSiteList();
        return R.ok().putBodyByMap("siteList", list);
    }

    @Override
    public R addCollect(@RequestBody JSONObject jsonObject) throws Exception {
        String siteId = jsonObject.getString("siteId");
        Assert.hasText(siteId, "参数错误！！");
        userService.addCollect(siteId, "add");
        return R.ok();
    }

    @Override
    public R addCopyApp(@RequestBody JSONObject jsonObject) throws Exception {
        String group = jsonObject.getString("group");
        Assert.hasText(group, "参数错误！！");
        Collect copy = userService.addCollect(group, "copy");
        Map<String, String> map = new HashMap<>();
        map.put("group", copy.getGroup());
        map.put("id", copy.getSiteId());
        map.put("index", copy.getIndex());
        return R.ok().putBodyByObject(map);
    }

    @Override
    public R deleteCollect(@RequestBody JSONObject jsonObject) throws Exception {
        String siteId = jsonObject.getString("siteId");
        Assert.hasText(siteId, "参数错误！！");
        collectService.deleteCollect(siteId);
        return R.ok();
    }

    @Override
    public R getSiteConfig(@RequestBody JSONObject jsonObject) throws Exception {
        String siteId = jsonObject.getString("siteId");
        Assert.hasText(siteId, "参数错误！！");
        String qxx_cookie = this.platformInfoService.getById("qdd_cookie").getValue();
        String qdd_url = platformInfoService.getById("qdd_url").getValue();
        Connection.Response execute = Jsoup.connect(qdd_url + "/user/get_site_config")
                .header("token", qxx_cookie)
                .data("siteId", siteId)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
        JSONObject body = JSONObject.parseObject(execute.body()).getJSONObject("body");
        return R.ok().putBodyByObject(body);
    }

    @Override
    public R updateCollect(@RequestBody JSONObject jsonObject) throws Exception {
        Integer collectId = jsonObject.getInteger("collectId");
        Assert.notNull(collectId, "参数错误！！");
        String siteName = jsonObject.getString("siteName");
        Assert.hasText(siteName, "参数错误！！");
        this.collectService.updateCollect(collectId, siteName);
        return R.ok();
    }

    @Override
    public R getCollectList() throws Exception {
        List<Collect> result = collectService.getCollectList();
        return R.ok().putBodyByMap("collectList", result);
    }

    @Override
    public R getOrdersList() throws Exception {
        String result = ordersService.getOrders();
        return R.ok().putBodyByMap("orders", StringUtils.delimitedListToStringArray(result, ","));
    }

    @Override
    public R saveOrdersList(@RequestBody SaveOrdersDTO saveOrdersDTO) throws Exception {
        ordersService.saveOrders(saveOrdersDTO.getOrders());
        return R.ok();
    }

    @Override
    public R getAccountList(@RequestBody RecordDTO recordDTO) throws Exception {
        // TODO
        return R.ok().putBodyByMap("records", new ArrayList<>());
    }

    @Override
    public R getProductList() throws Exception {
        // TODO
        return R.ok();
    }

    @Override
    public R getFriendNum() throws Exception {
        Long count = userService.countFriend();
        return R.ok().putBodyByMap("count", count);
    }

    @Override
    public R getBonusListByPage(@RequestBody PageDTO pageDTO) throws Exception {
        // TODO
        return R.ok().putBodyByMap("bonusList", new ArrayList<>());
    }

    @Override
    public R getFriendListByPage(@RequestBody PageDTO pageDTO) throws Exception {
        List<User> list = this.userService.getFriendListByPage(pageDTO);
        List<Map<String, String>> result = list.stream().map(o -> {
            Map<String, String> map = new HashMap<>();
            map.put("phone", o.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
            map.put("addTime", o.getCreateTime().toLocalDate().toString());
            return map;
        }).collect(Collectors.toList());
        return R.ok().putBodyByMap("users", result);
    }

    @Override
    public R getCodeNum() throws Exception {
        // TODO
        return R.ok().putBodyByMap("count", 100);
    }

    @Override
    public R getPointList() throws Exception {
        // TODO
        return R.ok().putBodyByMap("pointList", new ArrayList<>());
    }

    @Override
    public R payCode(@RequestBody JSONObject jsonObject) throws Exception {
        String code = jsonObject.getString("code");
        Assert.hasText(code, "请输入卡密");
        boolean result = userService.payCode(code);
        if (!result) {
            return R.error(500, "卡密错误");
        }
        return R.ok();
    }

    @Override
    public R getSets() throws Exception {
        return R.ok().putBodyByMap("set", this.platformInfoService.getSets());
    }

    @Override
    public R getBonusInfo() throws Exception {
        return R.ok().putBodyByMap("bonusInfo", this.platformInfoService.getBonusInfo());
    }

    @Override
    public R getWithdrawList() throws Exception {
        // TODO
        return R.ok().putBodyByMap("withdrawList", new ArrayList<>());
    }

    @Override
    public R getInviteList() throws Exception {
        // TODO
        return R.ok().putBodyByMap("inviteList", new ArrayList<>());
    }

    @Override
    public R canRun(@RequestBody SiteDTO siteDTO) throws Exception {
        // 抢多多官方这里加了日志记录，依旧不安全，只需要拦截前端请求，伪装成免费平台即可破解封号，垃圾设计
        boolean isVip = this.userService.getUser().getExpireTime().compareTo(LocalDate.now()) > 0;
        if (!isVip) {
            boolean isFree = this.siteService.getById(siteDTO.getGroup()).getIsFree() == 1;
            if (!isFree) {
                return R.ok("该平台仅支持会员使用，请充值会员再使用").putBodyByMap("enable", false);
            } else if (siteDTO.getGroup().equals(siteDTO.getSiteId())){
                return R.ok("分身功能仅支持会员使用，请充值会员再使用").putBodyByMap("enable", false);
            }
        }
        return R.ok().putBodyByMap("enable", true);
    }

    @Override
    public R getSuccessOrder(@RequestBody SiteDTO siteDTO) throws Exception {
        // TODO
        return R.ok().putBodyByMap("records", new ArrayList<>());
    }

    @Override
    public R addAdvice() throws Exception {
        return R.ok();
    }

    @Override
    public R getUserPlatphoms() throws Exception {
        // TODO
        return R.ok().putBodyByMap("platforms", new ArrayList<>());
    }

    /**
     * 记账功能  添加平台
     *
     * @return
     * @throws Exception
     */
    @Override
    public R addUserPlatphom(@RequestBody SiteDTO siteDTO) throws Exception {
        // TODO
        return R.ok();
    }

    /**
     * 记账功能  添加平台
     *
     * @return
     * @throws Exception
     */
    @Override
    public R deleteUserPlatphom(@RequestBody SiteDTO siteDTO) throws Exception {
        // TODO
        return R.ok();
    }

    @Override
    public R copyAccountList(@RequestBody JSONObject jsonObject) throws Exception {
        // TODO
        return R.ok().putBodyByMap("guid", IdUtils.fastUUID());
    }

    @Override
    public R addAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        // TODO
        return R.ok();
    }

    @Override
    public R deleteAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        // TODO
        return R.ok();
    }

    @Override
    public R getMemoList(@RequestBody RecordDTO recordDTO) throws Exception {
        // TODO
        return R.ok().putBodyByMap("records", new ArrayList<>());
    }

    @Override
    public R addMemo(@RequestBody AccountDTO recordDTO) throws Exception {
        // TODO
        return R.ok();
    }

    @Override
    public R updateMemoReturn(@RequestBody AccountDTO recordDTO) throws Exception {
        // TODO
        return R.ok();
    }

    @Override
    public R updateUserData(@RequestBody UserPlatformDTO userPlatformDTO) throws Exception {
        this.platformInfoBakService.update(userPlatformDTO);
        return R.ok();
    }

    @Override
    public R getUserData(@RequestBody JSONObject jsonObject) throws Exception {
        String password = jsonObject.getString("password");
        Assert.hasText(password, "参数错误！！");
        String data = this.platformInfoBakService.queryByUserId(password);
        if (StringUtils.hasText(data)) {
            return R.ok().putBodyByMap("data", data);
        }
        return R.error(500, "");
    }
}
