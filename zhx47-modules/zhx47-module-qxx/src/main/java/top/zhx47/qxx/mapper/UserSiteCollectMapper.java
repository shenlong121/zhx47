package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zhx47.qxx.datasource.entity.UserSiteCollect;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 22:28
 */
@Mapper
public interface UserSiteCollectMapper extends BaseMapper<UserSiteCollect> {
    List<UserSiteCollect> queryByUserId(String userId);

    List<UserSiteCollect> queryByGroupAndUserId(@Param("siteId") String siteId, @Param("userId") String userId);

    void deleteUserSiteCollect(@Param("siteId") String siteId, @Param("userId") Integer userId);

    void updateUserSiteCollect(@Param("collectId") Integer collectId, @Param("siteName") String siteName);
}
