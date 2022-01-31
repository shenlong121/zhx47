package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.qxx.datasource.entity.SysProduct;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysProductMapper extends BaseMapper<SysProduct> {
}
