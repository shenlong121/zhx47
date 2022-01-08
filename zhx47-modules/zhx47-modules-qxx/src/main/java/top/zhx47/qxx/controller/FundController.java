package top.zhx47.qxx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.utils.AlipayUtils;
import top.zhx47.common.core.utils.StringUtils;
import top.zhx47.qxx.api.controller.FundControllerApi;
import top.zhx47.qxx.datasource.entity.SysOrder;
import top.zhx47.qxx.datasource.po.SystemInfoPO;
import top.zhx47.qxx.service.PlatformInfoService;
import top.zhx47.qxx.service.SysOrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

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
    @Autowired
    private PlatformInfoService platformInfoService;
    @Autowired
    private SystemInfoPO systemInfoPO;

    @Override
    public void alipay(@RequestParam("setType") String setType, @RequestParam("token") String token,
                       HttpServletResponse response) throws IOException {
        setType = "price" + StringUtils.firstUpperCase(setType);
        String price = platformInfoService.getById(setType).getValue();
        SysOrder sysOrder = new SysOrder();
        sysOrder.setTotalAmount(new BigDecimal(price).setScale(2).toString());
        this.sysOrderService.save(sysOrder);
        response.sendRedirect(AlipayUtils.getQrCode(sysOrder.getOutTradeNo().toString(), "抢夕夕会员", price, "抢夕夕会员", this.systemInfoPO.getQxxApi() + "/common/notify"));
    }
}
