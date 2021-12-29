package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;
import top.zhx47.qxx.datasource.entity.Orders;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 23:06
 */
public interface OrdersService extends IService<Orders> {
    String getOrders();

    void saveOrders(List<String> orders);
}
