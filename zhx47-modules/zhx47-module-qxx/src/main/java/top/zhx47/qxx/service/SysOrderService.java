package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.datasource.entity.SysOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SysOrderService extends IService<SysOrder> {

    /**
     * 支付宝回调接口
     */
    void notify(HttpServletRequest request, HttpServletResponse response);
}
