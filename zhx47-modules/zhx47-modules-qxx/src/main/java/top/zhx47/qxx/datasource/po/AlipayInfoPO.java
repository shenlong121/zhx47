package top.zhx47.qxx.datasource.po;

import lombok.Data;

/**
 * @Author: 张许
 * @Description: 阿里巴巴扫码付款应用信息
 * @Date: 2021/12/31 09:18
 */
@Data
public class AlipayInfoPO {

    /**
     * 支付网关
     */
    private String gateway;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用私钥
     */
    private String appPrivateKey;

    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;

    /**
     * 支付宝开放平台账号ID
     */
    private String sellerId;
}
