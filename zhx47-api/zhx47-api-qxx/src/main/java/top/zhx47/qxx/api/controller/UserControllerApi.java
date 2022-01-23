package top.zhx47.qxx.api.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.web.R;
import top.zhx47.qxx.api.datasource.dto.*;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/16 23:12
 */
@RestController
@RequestMapping("/user")
public interface UserControllerApi {
    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/get_user", method = RequestMethod.POST)
    R getUser() throws Exception;

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/get_user_detail", method = RequestMethod.POST)
    R getUserDetail() throws Exception;

    /**
     * 获取所有平台列表
     */
    @RequestMapping(value = "/get_site_list", method = RequestMethod.POST)
    R getSiteList() throws Exception;

    /**
     * 用户添加平台
     */
    @RequestMapping(value = "/add_collect", method = RequestMethod.POST)
    R addCollect(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 用户添加平台分身
     */
    @RequestMapping(value = "/add_copy_app", method = RequestMethod.POST)
    R addCopyApp(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 移除用户已添加的平台
     */
    @RequestMapping(value = "/delete_collect", method = RequestMethod.POST)
    R deleteCollect(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 获取平台的自定义配置
     */
    @RequestMapping(value = "/get_site_config", method = RequestMethod.POST)
    R getSiteConfig(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 给平台起别名
     */
    @RequestMapping(value = "/update_collect", method = RequestMethod.POST)
    R updateCollect(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 获取用户平台列表
     */
    @RequestMapping(value = "/get_collect_list", method = RequestMethod.POST)
    R getCollectList() throws Exception;

    /**
     * 获取用户平台的排列顺序
     */
    @RequestMapping(value = "/get_orders_list", method = RequestMethod.POST)
    R getOrdersList() throws Exception;

    /**
     * 保存排序顺序
     */
    @RequestMapping(value = "/save_orders_list", method = RequestMethod.POST)
    R saveOrdersList(@RequestBody SaveOrdersDTO saveOrdersDTO) throws Exception;

    /**
     * 获取用户的网赚记账信息
     */
    @RequestMapping(value = "/get_account_list", method = RequestMethod.POST)
    R getAccountList(@RequestBody RecordDTO recordDTO) throws Exception;

    /**
     * 获取兑换奖品信息
     */
    @RequestMapping(value = "/get_product_list", method = RequestMethod.POST)
    R getProductList() throws Exception;

    /**
     * 获取兑换奖品信息
     */
    @RequestMapping(value = "/get_friend_num", method = RequestMethod.POST)
    R getFriendNum() throws Exception;

    /**
     * 获取余额明细
     */
    @RequestMapping(value = "/get_bonus_list_by_page", method = RequestMethod.POST)
    R getBonusListByPage(@RequestBody PageDTO pageDTO) throws Exception;

    /**
     * 获取好友列表
     */
    @RequestMapping(value = "/get_friend_list_by_page", method = RequestMethod.POST)
    R getFriendListByPage(@RequestBody PageDTO pageDTO) throws Exception;

    /**
     * 用户拥有卡密数量
     */
    @RequestMapping(value = "/get_code_num", method = RequestMethod.POST)
    R getCodeNum() throws Exception;

    /**
     * 获取积分明细
     */
    @RequestMapping(value = "/get_point_list", method = RequestMethod.POST)
    R getPointList() throws Exception;

    /**
     * 使用卡密重置会员
     */
    @RequestMapping(value = "/pay_code", method = RequestMethod.POST)
    R payCode(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 获取会员价格
     */
    @RequestMapping(value = "/get_sets", method = RequestMethod.POST)
    R getSets() throws Exception;

    /**
     * 获取下级充值提成信息
     */
    @RequestMapping(value = "/get_bonus_info", method = RequestMethod.POST)
    R getBonusInfo() throws Exception;

    /**
     * 提现明细
     */
    @RequestMapping(value = "/get_withdraw_list", method = RequestMethod.POST)
    R getWithdrawList() throws Exception;

    /**
     * 推荐人排行
     */
    @RequestMapping(value = "/get_invite_list", method = RequestMethod.POST)
    R getInviteList() throws Exception;

    /**
     * 判断平台是否可以执行（是否会员）
     */
    @RequestMapping(value = "/can_run", method = RequestMethod.POST)
    R canRun(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 用户当日抢单成功的平台
     */
    @RequestMapping(value = "/get_success_order", method = RequestMethod.POST)
    R getSuccessOrder(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 问题反馈,添加平台  不实现
     */
    @RequestMapping(value = {"/add_advice", "/add_advice_app"}, method = RequestMethod.POST)
    R addAdvice() throws Exception;

    /**
     * 记账功能  获取用户已经收藏的平台
     */
    @RequestMapping(value = "/get_user_platphoms", method = RequestMethod.POST)
    R getUserPlatphoms() throws Exception;

    /**
     * 记账功能  添加平台
     */
    @RequestMapping(value = "/add_user_platphom", method = RequestMethod.POST)
    R addUserPlatphom(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 记账功能  添加平台
     */
    @RequestMapping(value = "/delete_user_platphom", method = RequestMethod.POST)
    R deleteUserPlatphom(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 记账功能  导出账单
     */
    @RequestMapping(value = "/copy_account_list", method = RequestMethod.POST)
    R copyAccountList(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 记账功能  记账
     */
    @RequestMapping(value = "/add_account", method = RequestMethod.POST)
    R addAccount(@RequestBody AccountDTO accountDTO) throws Exception;

    /**
     * 记账功能  删除记账
     */
    @RequestMapping(value = "/delete_account", method = RequestMethod.POST)
    R deleteAccount(@RequestBody AccountDTO accountDTO) throws Exception;

    /**
     * 记账功能  获取提现记账记录
     */
    @RequestMapping(value = "/get_memo_list", method = RequestMethod.POST)
    R getMemoList(@RequestBody RecordDTO recordDTO) throws Exception;

    /**
     * 记账功能  添加提现记账记录
     */
    @RequestMapping(value = "/add_memo", method = RequestMethod.POST)
    R addMemo(@RequestBody AccountDTO recordDTO) throws Exception;

    /**
     * 记账功能  更新提现状态
     */
    @RequestMapping(value = "/update_memo_return", method = RequestMethod.POST)
    R updateMemoReturn(@RequestBody AccountDTO recordDTO) throws Exception;

    /**
     * 用户备份数据
     */
    @RequestMapping(value = "/update_user_data", method = RequestMethod.POST)
    R updateUserData(@RequestBody UserPlatformDTO userPlatformDTO) throws Exception;

    /**
     * 用户恢复数据
     */
    @RequestMapping(value = "/get_user_data", method = RequestMethod.POST)
    R getUserData(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 兑换礼品
     */
    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
    R addOrder(@RequestBody JSONObject jsonObject);

    /**
     * 兑换礼品记录
     */
    @RequestMapping(value = "/get_order_list", method = RequestMethod.POST)
    R getOrderList();

    /**
     * 设置收货地址
     */
    @RequestMapping(value = "/add_address", method = RequestMethod.POST)
    R addAddress(@RequestBody JSONObject jsonObject);

    /**
     * 获取用户收货地址
     */
    @RequestMapping(value = "/get_address", method = RequestMethod.POST)
    R getAddress();
}
