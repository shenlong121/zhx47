package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.datasource.entity.PlatformInfo;
import top.zhx47.qxx.datasource.po.SystemInfoPO;

import java.util.Map;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/30 21:41
 */
public interface PlatformInfoService extends IService<PlatformInfo> {
    void updateQDDCookie(String token);

    Map<String, Integer> getBonusInfo();

    Map<String, Integer> getSets();

    SystemInfoPO getSystemInfoPO() throws IllegalAccessException;

    void updateQDDURL(String url);
}
