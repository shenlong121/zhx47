package top.zhx47.qxx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.utils.AlipayUtils;
import top.zhx47.qxx.api.controller.FundControllerApi;
import top.zhx47.qxx.datasource.entity.SysOrder;
import top.zhx47.qxx.service.SysOrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/12/30 20:46
 */
@RestController
@RequestMapping("/fund")
public class FundController implements FundControllerApi {

    @Autowired
    private SysOrderService sysOrderService;

    @Override
    public void alipay(@RequestParam("setType") String setType, @RequestParam("token") String token,
                       HttpServletResponse response) throws IOException {
        SysOrder sysOrder = new SysOrder();
        this.sysOrderService.save(sysOrder);
        response.sendRedirect(AlipayUtils.getQrCode(sysOrder.getTradeNo().toString(), "测试商品", "0.01", "测试商品测试商品", "http://zhx47.vaiwan.com/qxx/common/notify"));
    }
}
