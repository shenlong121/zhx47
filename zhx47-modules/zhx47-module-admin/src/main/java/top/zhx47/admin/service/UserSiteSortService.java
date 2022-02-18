package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.UserSiteSort;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 23:06
 */
public interface UserSiteSortService extends IService<UserSiteSort> {
    String getUserSiteSort();

    void saveUserSiteSort(List<String> userSiteSort);
}
