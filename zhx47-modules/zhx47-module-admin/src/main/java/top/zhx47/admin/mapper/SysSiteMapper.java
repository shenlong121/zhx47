package top.zhx47.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.admin.datasource.entity.SysSite;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:11
 */
@Mapper
public interface SysSiteMapper extends BaseMapper<SysSite> {
    List<SysSite> getSysSiteList();
}
