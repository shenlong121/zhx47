package top.zhx47.api.qxx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/12/30 20:44
 */
@RestController
@RequestMapping("/fund")
public interface FundControllerApi {

    /**
     * 购买会员
     * 支付宝当面付扫码支付，但是可以不生成二维码，直接浏览器跳转链接唤醒手机支付宝
     *
     * @param setType  购买的标签
     * @param token    用户的Token，如果没有使用Nginx，会导致Request Header中无法携带Token
     * @param response 用于跳转支付链接
     */
    @RequestMapping(value = "/alipay", method = RequestMethod.GET)
    void alipay(@RequestParam("setType") String setType, @RequestParam("token") String token,
                HttpServletResponse response) throws IOException;
}
