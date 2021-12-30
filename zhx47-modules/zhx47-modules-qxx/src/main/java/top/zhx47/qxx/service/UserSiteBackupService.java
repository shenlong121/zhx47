package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.api.datasource.dto.UserPlatformDTO;
import top.zhx47.qxx.datasource.entity.UserSiteBackup;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/9/22 20:24
 */
public interface UserSiteBackupService extends IService<UserSiteBackup> {
    void update(UserPlatformDTO userPlatformDTO);

    String queryByUserId(String password);
}
