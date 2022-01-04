package top.zhx47.common.core.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author: 张许
 * @Description: 阿里巴巴支付工具
 * @Date: 2021/12/31 10:12
 */
public class AlipayUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayUtils.class);
    private static AlipayClient alipayClient;

    private AlipayUtils() {
    }

    /**
     * 初始化一个AlipayClient
     *
     * @param gateway         支付宝网关
     * @param appId           应用ID
     * @param appPrivateKey   应用私钥
     * @param alipayPublicKey 应用公钥
     */
    public static void getAlipayClientInstance(String gateway, String appId, String appPrivateKey, String alipayPublicKey) {
        alipayClient = new DefaultAlipayClient(gateway, appId, appPrivateKey, "json", "UTF-8", alipayPublicKey, "RSA2");
    }

    /**
     * 获取支付宝收款码
     *
     * @param billNo         系统订单编号
     * @param productName    商品名称
     * @param productPrice   商品价格
     * @param productDetails 商品详情
     * @param notifyUrl      回调接口
     * @return 收款码二维码链接
     */
    public static String getQrCode(String billNo, String productName, String productPrice, String productDetails, String notifyUrl) {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(billNo);
        model.setProductCode("FACE_TO_FACE_PAYMENT");
        model.setSubject(productName);
        model.setTotalAmount(productPrice);
        model.setBody(productDetails);
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.getQrCode();
    }

    /**
     * 支付宝异步通知验签
     *
     * @param param           请求
     * @param alipayPublicKey 支付宝公钥
     * @return 验签结果
     */
    public static boolean signVerification(Map<String, String> param, String alipayPublicKey) throws AlipayApiException {
        param.remove("sign_type");
        boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(param, alipayPublicKey, "UTF-8", "RSA2");
        LOGGER.info("支付宝验签结果：{}", alipayRSACheckedV2);
        return alipayRSACheckedV2;
    }
}
