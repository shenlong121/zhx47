package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.qxx.datasource.entity.Orders;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 23:07
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    Orders queryByUserId(String userId);
}
