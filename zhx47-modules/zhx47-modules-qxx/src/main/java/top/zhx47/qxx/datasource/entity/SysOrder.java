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
    * 系统订单
    */
    @TableId(value = "trade_no", type = IdType.AUTO)
    private Long tradeNo;

    /**
    * 是否付款 -- [0：未付款  1：已付款]
    */
    private Boolean isPay;
}