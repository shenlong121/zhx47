package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.datasource.entity.UserSiteSort;
import top.zhx47.qxx.mapper.UserSiteSortMapper;
import top.zhx47.qxx.service.UserSiteSortService;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 23:07
 */
@Service
public class UserSiteSortServiceImpl extends ServiceImpl<UserSiteSortMapper, UserSiteSort> implements UserSiteSortService {
    @Override
    public String getUserSiteSort() {
        UserSiteSort userSiteSort = this.baseMapper.queryByUserId(SecurityUtils.getUsername());
        return userSiteSort == null ? "" : userSiteSort.getOrder();
    }

    @Override
    @Transactional
    public void saveUserSiteSort(List<String> ordersValue) {
        String order = String.join(",", ordersValue);
        this.saveOrUpdate(new UserSiteSort(SecurityUtils.getUsername(), order));
    }
}
