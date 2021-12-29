package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.datasource.entity.Orders;
import top.zhx47.qxx.mapper.OrdersMapper;
import top.zhx47.qxx.service.OrdersService;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 23:07
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Override
    public String getOrders() {
        Orders orders = baseMapper.queryByUserId(SecurityUtils.getUsername());
        return orders == null ? "" : orders.getOrder();
    }

    @Override
    @Transactional
    public void saveOrders(List<String> ordersValue) {
        String order = String.join(",", ordersValue);
        saveOrUpdate(new Orders(SecurityUtils.getUsername(), order));
    }
}
