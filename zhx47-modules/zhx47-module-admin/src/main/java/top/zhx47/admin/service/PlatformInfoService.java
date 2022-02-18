package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.PlatformInfo;

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

    void updateQDDURL(String url);

    Integer getVIPDaysByPrice(String totalAmount);
}
