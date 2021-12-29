package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zhx47.qxx.datasource.entity.Collect;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 22:28
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    List<Collect> queryByUserId(String userId);

    List<Collect> queryByGroupAndUserId(@Param("siteId") String siteId, @Param("userId") String userId);

    void deleteCollect(@Param("siteId") String siteId, @Param("userId") Integer userId);

    void updateCollect(@Param("collectId") Integer collectId, @Param("siteName") String siteName);
}
