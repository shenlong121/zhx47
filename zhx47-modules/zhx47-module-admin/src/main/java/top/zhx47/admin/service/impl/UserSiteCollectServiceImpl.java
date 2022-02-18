package top.zhx47.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.admin.datasource.entity.UserSiteCollect;
import top.zhx47.admin.mapper.UserSiteCollectMapper;
import top.zhx47.admin.service.UserSiteCollectService;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 22:28
 */
@Service
public class UserSiteCollectServiceImpl extends ServiceImpl<UserSiteCollectMapper, UserSiteCollect> implements UserSiteCollectService {
    @Override
    public List<UserSiteCollect> getUserSiteCollectList() {
        return baseMapper.queryByUserId(SecurityUtils.getUsername());
    }

    @Override
    public List<UserSiteCollect> queryByGroupAndUserId(String siteId, String userId) {
        return baseMapper.queryByGroupAndUserId(siteId, userId);
    }

    @Override
    public void deleteUserSiteCollect(String siteId) {
        baseMapper.deleteUserSiteCollect(siteId, SecurityUtils.getId());
    }

    @Override
    public void updateUserSiteCollect(Integer collectId, String siteName) {
        baseMapper.updateUserSiteCollect(collectId, siteName);
    }
}
