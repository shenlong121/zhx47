package top.zhx47.qxx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.api.datasource.dto.UserPlatformDTO;
import top.zhx47.qxx.datasource.entity.UserSiteBackup;
import top.zhx47.qxx.mapper.UserSiteBackupMapper;
import top.zhx47.qxx.service.UserSiteBackupService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/9/22 20:24
 */
@Service
public class UserSiteBackupServiceImpl extends ServiceImpl<UserSiteBackupMapper, UserSiteBackup> implements UserSiteBackupService {
    @Override
    @Transactional
    public void update(UserPlatformDTO userPlatformDTO) {
        UserSiteBackup userSiteBackup = new UserSiteBackup(SecurityUtils.getId(), JSONObject.toJSONString(userPlatformDTO.getData()), userPlatformDTO.getPassword());
        this.saveOrUpdate(userSiteBackup);
    }

    @Override
    public String queryByUserId(String password) {
        UserSiteBackup userSiteBackup = this.getById(SecurityUtils.getId());
        if (userSiteBackup != null && userSiteBackup.getPassword().equals(password)) {
            return userSiteBackup.getInfo();
        }
        return null;
    }
}
