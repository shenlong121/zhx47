package top.zhx47.qxx.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.utils.IdUtils;
import top.zhx47.common.core.web.R;
import top.zhx47.qxx.api.controller.UserControllerApi;
import top.zhx47.qxx.api.datasource.dto.*;
import top.zhx47.qxx.datasource.entity.SysSite;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.datasource.entity.UserSiteCollect;
import top.zhx47.qxx.service.*;

import java.io.IOException;
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
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    private final UserService userService;
    private final SysSiteService sysSiteService;
    private final UserSiteCollectService userSiteCollectService;
    private final UserSiteSortService userSiteSortService;
    private final PlatformInfoService platformInfoService;
    private final UserSiteBackupService userSiteBackupService;
    private final SysProductService sysProductService;

    @Override
    public R getUser() {
        User user = userService.getUser();
        return R.ok().putBodyByMap("user", user);
    }

    @Override
    public R getUserDetail() {
        return this.getUser();
    }

    @Override
    public R getSiteList() {
        List<SysSite> list = sysSiteService.getSysSiteList();
        return R.ok().putBodyByMap("siteList", list);
    }

    @Override
    public R addCollect(@RequestBody JSONObject jsonObject) {
        String siteId = jsonObject.getString("siteId");
        Assert.hasText(siteId, "参数错误！！");
        userService.addCollect(siteId, "add");
        return R.ok();
    }

    @Override
    public R addCopyApp(@RequestBody JSONObject jsonObject) {
        String group = jsonObject.getString("group");
        Assert.hasText(group, "参数错误！！");
        UserSiteCollect copy = userService.addCollect(group, "copy");
        Map<String, String> map = new HashMap<>();
        map.put("group", copy.getGroup());
        map.put("id", copy.getSiteId());
        map.put("index", copy.getIndex());
        return R.ok().putBodyByObject(map);
    }

    @Override
    public R deleteCollect(@RequestBody JSONObject jsonObject) {
        String siteId = jsonObject.getString("siteId");
        Assert.hasText(siteId, "参数错误！！");
        userSiteCollectService.deleteUserSiteCollect(siteId);
        return R.ok();
    }

    @Override
    public R getSiteConfig(@RequestBody JSONObject jsonObject) throws IOException {
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
    public R updateCollect(@RequestBody JSONObject jsonObject) {
        Integer collectId = jsonObject.getInteger("collectId");
        Assert.notNull(collectId, "参数错误！！");
        String siteName = jsonObject.getString("siteName");
        Assert.hasText(siteName, "参数错误！！");
        this.userSiteCollectService.updateUserSiteCollect(collectId, siteName);
        return R.ok();
    }

    @Override
    public R getCollectList() {
        List<UserSiteCollect> result = userSiteCollectService.getUserSiteCollectList();
        return R.ok().putBodyByMap("collectList", result);
    }

    @Override
    public R getOrdersList() {
        String result = userSiteSortService.getUserSiteSort();
        return R.ok().putBodyByMap("orders", StringUtils.delimitedListToStringArray(result, ","));
    }

    @Override
    public R saveOrdersList(@RequestBody SaveOrdersDTO saveOrdersDTO) {
        userSiteSortService.saveUserSiteSort(saveOrdersDTO.getOrders());
        return R.ok();
    }

    @Override
    public R getAccountList(@RequestBody RecordDTO recordDTO) {
        // TODO
        return R.ok().putBodyByMap("records", new ArrayList<>());
    }

    @Override
    public R getProductList() {
        // TODO
        return R.ok().putBodyByMap("productList", this.sysProductService.list());
    }

    @Override
    public R getFriendNum() {
        Long count = userService.countFriend();
        return R.ok().putBodyByMap("count", count);
    }

    @Override
    public R getBonusListByPage(@RequestBody PageDTO pageDTO) {
        // TODO
        return R.ok().putBodyByMap("bonusList", new ArrayList<>());
    }

    @Override
    public R getFriendListByPage(@RequestBody PageDTO pageDTO) {
        List<User> list = this.userService.getFriendListByPage(pageDTO);
        List<Map<String, String>> result = list.stream().map(o -> {
            Map<String, String> map = new HashMap<>();
            map.put("phone", o.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            map.put("addTime", o.getCreateTime().toLocalDate().toString());
            return map;
        }).collect(Collectors.toList());
        return R.ok().putBodyByMap("users", result);
    }

    @Override
    public R getCodeNum() {
        // TODO
        return R.ok().putBodyByMap("count", 100);
    }

    @Override
    public R getPointList() {
        // TODO
        return R.ok().putBodyByMap("pointList", new ArrayList<>());
    }

    @Override
    public R payCode(@RequestBody JSONObject jsonObject) {
        String code = jsonObject.getString("code");
        Assert.hasText(code, "请输入卡密");
        boolean result = userService.payCode(code);
        if (!result) {
            return R.error(500, "卡密错误");
        }
        return R.ok();
    }

    @Override
    public R getSets() {
        return R.ok().putBodyByMap("set", this.platformInfoService.getSets());
    }

    @Override
    public R getBonusInfo() {
        return R.ok().putBodyByMap("bonusInfo", this.platformInfoService.getBonusInfo());
    }

    @Override
    public R getWithdrawList() {
        // TODO
        return R.ok().putBodyByMap("withdrawList", new ArrayList<>());
    }

    @Override
    public R getInviteList() {
        // TODO
        return R.ok().putBodyByMap("inviteList", new ArrayList<>());
    }

    @Override
    public R canRun(@RequestBody SiteDTO siteDTO) {
        // 抢多多官方这里加了日志记录，依旧不安全，只需要拦截前端请求，伪装成免费平台即可破解封号，垃圾设计
        boolean isVip = this.userService.getUser().getExpireTime().compareTo(LocalDate.now()) > 0;
        if (!isVip) {
            boolean isFree = this.sysSiteService.getById(siteDTO.getGroup()).getIsFree() == 1;
            if (!isFree) {
                return R.ok("该平台仅支持会员使用，请充值会员再使用").putBodyByMap("enable", false);
            } else if (siteDTO.getGroup().equals(siteDTO.getSiteId())) {
                return R.ok("分身功能仅支持会员使用，请充值会员再使用").putBodyByMap("enable", false);
            }
        }
        return R.ok().putBodyByMap("enable", true);
    }

    @Override
    public R getSuccessOrder(@RequestBody SiteDTO siteDTO) {
        // TODO
        return R.ok().putBodyByMap("records", new ArrayList<>());
    }

    @Override
    public R addAdvice() {
        return R.ok();
    }

    @Override
    public R getUserPlatphoms() {
        // TODO
        return R.ok().putBodyByMap("platforms", new ArrayList<>());
    }

    @Override
    public R addUserPlatphom(@RequestBody SiteDTO siteDTO) {
        // TODO
        return R.ok();
    }

    @Override
    public R deleteUserPlatphom(@RequestBody SiteDTO siteDTO) {
        // TODO
        return R.ok();
    }

    @Override
    public R copyAccountList(@RequestBody JSONObject jsonObject) {
        // TODO
        return R.ok().putBodyByMap("guid", IdUtils.fastUUID());
    }

    @Override
    public R addAccount(@RequestBody AccountDTO accountDTO) {
        // TODO
        return R.ok();
    }

    @Override
    public R deleteAccount(@RequestBody AccountDTO accountDTO) {
        // TODO
        return R.ok();
    }

    @Override
    public R getMemoList(@RequestBody RecordDTO recordDTO) {
        // TODO
        return R.ok().putBodyByMap("records", new ArrayList<>());
    }

    @Override
    public R addMemo(@RequestBody AccountDTO recordDTO) {
        // TODO
        return R.ok();
    }

    @Override
    public R updateMemoReturn(@RequestBody AccountDTO recordDTO) {
        // TODO
        return R.ok();
    }

    @Override
    public R updateUserData(@RequestBody UserPlatformDTO userPlatformDTO) {
        this.userSiteBackupService.update(userPlatformDTO);
        return R.ok();
    }

    @Override
    public R getUserData(@RequestBody JSONObject jsonObject) {
        String password = jsonObject.getString("password");
        Assert.hasText(password, "参数错误！！");
        String data = this.userSiteBackupService.queryByUserId(password);
        if (StringUtils.hasText(data)) {
            return R.ok().putBodyByMap("data", data);
        }
        return R.error(500, "");
    }

    @Override
    public R addOrder(@RequestBody JSONObject jsonObject) {
        // TODO
        return R.ok();
    }

    @Override
    public R getOrderList() {
        // TODO
        return R.ok().putBodyByMap("orderList", new ArrayList<>());
    }

    @Override
    public R addAddress(@RequestBody ReceiveInfoDTO receiveInfoDTO) {
        this.userService.addAddress(receiveInfoDTO);
        return R.ok();
    }

    @Override
    public R getAddress() {
        List<ReceiveInfoDTO> list = this.userService.getAddress();
        return R.ok().putBodyByMap("addressList", list);
    }
}
