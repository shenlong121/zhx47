package top.zhx47.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.admin.datasource.entity.UserSiteBackup;
import top.zhx47.admin.mapper.UserSiteBackupMapper;
import top.zhx47.admin.service.UserSiteBackupService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/9/22 20:24
 */
@Service
public class UserSiteBackupServiceImpl extends ServiceImpl<UserSiteBackupMapper, UserSiteBackup> implements UserSiteBackupService {
    @Override
    public String queryByUserId(String password) {
        UserSiteBackup userSiteBackup = this.getById(SecurityUtils.getId());
        if (userSiteBackup != null && userSiteBackup.getPassword().equals(password)) {
            return userSiteBackup.getInfo();
        }
        return null;
    }
}
