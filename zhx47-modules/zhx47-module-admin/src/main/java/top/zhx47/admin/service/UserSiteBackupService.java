package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.UserSiteBackup;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/9/22 20:24
 */
public interface UserSiteBackupService extends IService<UserSiteBackup> {
    String queryByUserId(String password);
}
