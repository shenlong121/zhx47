package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;
import top.zhx47.qxx.datasource.entity.Collect;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/17 22:27
 */
public interface CollectService extends IService<Collect> {
    List<Collect> getCollectList();

    List<Collect> queryByGroupAndUserId(String siteId, String username);

    void deleteCollect(String siteId);

    void updateCollect(Integer collectId, String siteName);
}
