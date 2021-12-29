package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.qxx.datasource.entity.Site;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:11
 */
@Mapper
public interface SiteMapper extends BaseMapper<Site> {
    List<Site> getSiteList();
}
