package top.zhx47.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.admin.datasource.entity.UserSiteSort;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 23:07
 */
@Mapper
public interface UserSiteSortMapper extends BaseMapper<UserSiteSort> {
    UserSiteSort queryByUserId(String userId);
}
