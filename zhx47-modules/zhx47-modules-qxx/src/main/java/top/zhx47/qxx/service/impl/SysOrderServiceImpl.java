package top.zhx47.qxx.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.core.utils.AlipayUtils;
import top.zhx47.common.core.utils.ServletUtils;
import top.zhx47.qxx.datasource.entity.SysOrder;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.datasource.po.AlipayInfoPO;
import top.zhx47.qxx.mapper.SysOrderMapper;
import top.zhx47.qxx.service.PlatformInfoService;
import top.zhx47.qxx.service.SysOrderService;
import top.zhx47.qxx.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements SysOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysOrderServiceImpl.class);

    @Autowired
    private AlipayInfoPO alipayInfoPO;
    @Autowired
    private UserService userService;
    @Autowired
    private PlatformInfoService platformInfoService;

    @Override
    @Transactional
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        Map<String, String> param = new HashMap<>();
        for (Map.Entry<String, String[]> entry : entries) {
            LOGGER.info("支付宝参数：{} -> {}", entry.getKey(), String.join("", entry.getValue()));
            param.put(entry.getKey(), String.join("", entry.getValue()));
        }
        try {
            boolean alipayRSACheckedV2 = AlipayUtils.signVerification(param, this.alipayInfoPO.getAlipayPublicKey());
            if (!alipayRSACheckedV2) {
                ServletUtils.renderText(response, "fail");
            }
            //  验签成功，校验其他的参数
            SysOrder sysOrder = this.checkAlipayResponse(param);
            if (sysOrder != null) {
                // 更新订单状态，续费VIP
                this.updateById(sysOrder);
                this.renewMember(sysOrder.getCreateUser(), sysOrder.getTotalAmount());
                ServletUtils.renderText(response, "success");
            } else {
                ServletUtils.renderText(response, "fail");
            }
        } catch (AlipayApiException e) {
            ServletUtils.renderText(response, "fail");
        }
    }

    /**
     * 给用户充值会员
     *
     * @param userId  用户ID
     * @param totalAmount 付款（根据付款获得应充值的天数）
     */
    private void renewMember(Integer userId, String totalAmount) {
        User user = this.userService.getById(userId);
        Integer days = this.platformInfoService.getVIPDaysByPrice(totalAmount.substring(0, totalAmount.length() - 3));
        LocalDate expireTime = user.getExpireTime();
        if (expireTime.compareTo(LocalDate.now()) > 0) {
            // 当前为会员
            user.setExpireTime(expireTime.plusDays(days));
        } else {
            // 非会员
            user.setExpireTime(LocalDate.now().plusDays(days));
        }
        this.userService.updateById(user);
        LOGGER.info("用户: {} 续费会员 {} 天", userId, days);
    }

    /**
     * 校验支付宝参数
     */
    private SysOrder checkAlipayResponse(Map<String, String> param) {
        SysOrder alipayResponse = this.getAlipayResponse(param);
        /*
          1. 商户需要验证该通知数据中的 out_trade_no 是否为商户系统中创建的订单号；
          2. 判断 total_amount 是否确实为该订单的实际金额（即商户订单创建时的金额）；
          3. 校验通知中的 seller_id（或者 seller_email) 是否为 out_trade_no 这笔单据的对应的操作方（有的时候，一个商户可能有多个 seller_id/seller_email）；
          4. 验证 app_id 是否为该商户本身。
         */
        SysOrder sysOrder = this.getById(alipayResponse.getOutTradeNo());
        if (sysOrder == null || !sysOrder.getTotalAmount().equals(alipayResponse.getTotalAmount())) {
            return null;
        }
        if (param.get("seller_id").equals(this.alipayInfoPO.getSellerId()) && param.get("app_id").equals(this.alipayInfoPO.getAppId())) {
            Integer createUser = sysOrder.getCreateUser();
            BeanUtils.copyProperties(alipayResponse, sysOrder);
            sysOrder.setIsPay(true);
            sysOrder.setCreateUser(createUser);
            return sysOrder;
        }
        return null;
    }

    /**
     * 从支付宝回调信息中封装一个订单对象应用校验
     */
    private SysOrder getAlipayResponse(Map<String, String> param) {
        SysOrder sysOrder = new SysOrder();
        sysOrder.setOutTradeNo(Long.valueOf(param.get("out_trade_no")));
        sysOrder.setTradeNo(param.get("trade_no"));
        sysOrder.setTotalAmount(param.get("total_amount"));
        sysOrder.setReceiptAmount(param.get("receipt_amount"));
        return sysOrder;
    }
}
