package top.zhx47.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.admin.datasource.entity.PlatformInfo;
import top.zhx47.admin.datasource.enums.DaysEnum;
import top.zhx47.admin.mapper.PlatformInfoMapper;
import top.zhx47.admin.service.PlatformInfoService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/30 21:41
 */
@Service
public class PlatformInfoServiceImpl extends ServiceImpl<PlatformInfoMapper, PlatformInfo> implements PlatformInfoService {

    private static final Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     *
     * @param str 需要转换的字符串
     * @return 处理好的驼峰字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Override
    public void updateQDDCookie(String token) {
        this.baseMapper.updateQDDCookie(token);
    }

    @Override
    public Map<String, Integer> getBonusInfo() {
        List<Map<String, String>> bonusInfo = this.baseMapper.getBonusInfo();
        return bonusInfo.stream().collect(Collectors.toMap(map -> map.get("key"), item -> Integer.valueOf(item.get("value"))));
    }

    @Override
    public Map<String, Integer> getSets() {
        List<Map<String, String>> sets = this.baseMapper.getSets();
        return sets.stream().collect(Collectors.toMap(map -> map.get("key"), item -> Integer.valueOf(item.get("value"))));
    }

    @Override
    public void updateQDDURL(String url) {
        this.baseMapper.updateQDDURL(url);
    }

    @Override
    public Integer getVIPDaysByPrice(String totalAmount) {
        String key = this.baseMapper.getPriceKeyByValue(totalAmount);
        String type;
        if (key.startsWith("f")) {
            type = key.substring(4);
        } else {
            type = key.substring(5);
        }
        return DaysEnum.getDays(type);
    }
}
