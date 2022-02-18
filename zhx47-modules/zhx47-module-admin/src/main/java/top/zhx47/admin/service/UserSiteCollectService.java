package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.UserSiteCollect;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 22:27
 */
public interface UserSiteCollectService extends IService<UserSiteCollect> {
    List<UserSiteCollect> getUserSiteCollectList();

    List<UserSiteCollect> queryByGroupAndUserId(String siteId, String username);

    void deleteUserSiteCollect(String siteId);

    void updateUserSiteCollect(Integer collectId, String siteName);
}
