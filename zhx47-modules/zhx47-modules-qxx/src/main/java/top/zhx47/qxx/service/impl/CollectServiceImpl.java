package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.datasource.entity.Collect;
import top.zhx47.qxx.mapper.CollectMapper;
import top.zhx47.qxx.service.CollectService;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 22:28
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Override
    public List<Collect> getCollectList() {
        return baseMapper.queryByUserId(SecurityUtils.getUsername());
    }

    @Override
    public List<Collect> queryByGroupAndUserId(String siteId, String userId) {
        return baseMapper.queryByGroupAndUserId(siteId, userId);
    }

    @Override
    public void deleteCollect(String siteId) {
        baseMapper.deleteCollect(siteId, SecurityUtils.getId());
    }

    @Override
    public void updateCollect(Integer collectId, String siteName) {
        baseMapper.updateCollect(collectId, siteName);
    }
}
