package top.zhx47.qxx.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 支付订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysOrder extends BaseEntity {

    /**
     * 商家订单号
     */
    @TableId(value = "out_trade_no", type = IdType.AUTO)
    private Long outTradeNo;

    /**
     * 支付宝交易号，支付宝交易凭证号
     */
    private String tradeNo;

    /**
     * 订单金额。单位为人民币（元），精确到小数点后 2 位
     */
    private String totalAmount;

    /**
     * 实收金额
     */
    private String receiptAmount;

    /**
     * 是否付款 -- [0：未付款  1：已付款]
     */
    private Boolean isPay;
}