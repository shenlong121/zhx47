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
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_user", method = RequestMethod.POST)
    public R getUser() throws Exception;

    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_user_detail", method = RequestMethod.POST)
    public R getUserDetail() throws Exception;

    /**
     * 获取所有平台列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_site_list", method = RequestMethod.POST)
    public R getSiteList() throws Exception;

    /**
     * 用户添加平台
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add_collect", method = RequestMethod.POST)
    public R addCollect(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 用户添加平台分身
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add_copy_app", method = RequestMethod.POST)
    public R addCopyApp(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 移除用户已添加的平台
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete_collect", method = RequestMethod.POST)
    public R deleteCollect(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 获取平台的自定义配置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_site_config", method = RequestMethod.POST)
    public R getSiteConfig(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 给平台起别名
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update_collect", method = RequestMethod.POST)
    public R updateCollect(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 获取用户平台列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_collect_list", method = RequestMethod.POST)
    public R getCollectList() throws Exception;

    /**
     * 获取用户平台的排列顺序
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_orders_list", method = RequestMethod.POST)
    public R getOrdersList() throws Exception;

    /**
     * 保存排序顺序
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save_orders_list", method = RequestMethod.POST)
    public R saveOrdersList(@RequestBody SaveOrdersDTO saveOrdersDTO) throws Exception;

    /**
     * 获取用户的记账信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_account_list", method = RequestMethod.POST)
    public R getAccountList(@RequestBody RecordDTO recordDTO) throws Exception;

    /**
     * 获取兑换奖品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_product_list", method = RequestMethod.POST)
    public R getProductList() throws Exception;

    /**
     * 获取兑换奖品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_friend_num", method = RequestMethod.POST)
    public R getFriendNum() throws Exception;

    /**
     * 获取余额明细
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_bonus_list_by_page", method = RequestMethod.POST)
    public R getBonusListByPage(@RequestBody PageDTO pageDTO) throws Exception;

    /**
     * 获取好友列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_friend_list_by_page", method = RequestMethod.POST)
    public R getFriendListByPage(@RequestBody PageDTO pageDTO) throws Exception;

    /**
     * // TODO 不知道干嘛的 用户拥有卡密数量
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_code_num", method = RequestMethod.POST)
    public R getCodeNum() throws Exception;

    /**
     * 获取积分明细
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_point_list", method = RequestMethod.POST)
    public R getPointList() throws Exception;

    /**
     * 使用卡密重置会员
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pay_code", method = RequestMethod.POST)
    public R payCode(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 获取会员价格
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_sets", method = RequestMethod.POST)
    public R getSets() throws Exception;

    /**
     * 获取下级充值提成信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_bonus_info", method = RequestMethod.POST)
    public R getBonusInfo() throws Exception;

    /**
     * 提现明细
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_withdraw_list", method = RequestMethod.POST)
    public R getWithdrawList() throws Exception;

    /**
     * 推荐人排行
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_invite_list", method = RequestMethod.POST)
    public R getInviteList() throws Exception;

    /**
     * 判断平台是否可以执行（是否会员）
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/can_run", method = RequestMethod.POST)
    public R canRun(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 用户当日抢单成功的平台
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_success_order", method = RequestMethod.POST)
    public R getSuccessOrder(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 问题反馈,添加平台  不实现
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add_advice", "/add_advice_app"}, method = RequestMethod.POST)
    public R addAdvice() throws Exception;

    /**
     * 记账功能  获取用户已经收藏的平台
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_user_platphoms", method = RequestMethod.POST)
    public R getUserPlatphoms() throws Exception;

    /**
     * 记账功能  添加平台
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add_user_platphom", method = RequestMethod.POST)
    public R addUserPlatphom(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 记账功能  添加平台
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete_user_platphom", method = RequestMethod.POST)
    public R deleteUserPlatphom(@RequestBody SiteDTO siteDTO) throws Exception;

    /**
     * 记账功能  导出账单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/copy_account_list", method = RequestMethod.POST)
    public R copyAccountList(@RequestBody JSONObject jsonObject) throws Exception;

    /**
     * 记账功能  记账
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add_account", method = RequestMethod.POST)
    public R addAccount(@RequestBody AccountDTO accountDTO) throws Exception;

    /**
     * 记账功能  删除记账
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete_account", method = RequestMethod.POST)
    public R deleteAccount(@RequestBody AccountDTO accountDTO) throws Exception;

    /**
     * 记账功能  获取提现记账记录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_memo_list", method = RequestMethod.POST)
    public R getMemoList(@RequestBody RecordDTO recordDTO) throws Exception;

    /**
     * 记账功能  添加提现记账记录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add_memo", method = RequestMethod.POST)
    public R addMemo(@RequestBody AccountDTO recordDTO) throws Exception;

    /**
     * 记账功能  更新提现状态
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update_memo_return", method = RequestMethod.POST)
    public R updateMemoReturn(@RequestBody AccountDTO recordDTO) throws Exception;

    /**
     * 用户备份数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update_user_data", method = RequestMethod.POST)
    public R updateUserData(@RequestBody UserPlatformDTO userPlatformDTO) throws Exception;

    /**
     * 用户恢复数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_user_data", method = RequestMethod.POST)
    public R getUserData(@RequestBody JSONObject jsonObject) throws Exception;
}
