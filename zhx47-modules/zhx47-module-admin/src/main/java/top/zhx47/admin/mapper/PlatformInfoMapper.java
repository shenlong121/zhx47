package top.zhx47.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.admin.datasource.entity.PlatformInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/30 21:42
 */
@Mapper
public interface PlatformInfoMapper extends BaseMapper<PlatformInfo> {
    void updateQDDCookie(String token);

    List<Map<String, String>> getBonusInfo();

    List<Map<String, String>> getSets();

    List<Map<String, String>> getSystemInfo();

    void updateQDDURL(String url);

    List<Map<String, String>> getAlipayInfo();

    String getPriceKeyByValue(String totalAmount);
}
