package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.PlatformInfo;
import top.zhx47.qxx.datasource.po.AlipayInfoPO;
import top.zhx47.qxx.datasource.po.SystemInfoPO;
import top.zhx47.qxx.mapper.PlatformInfoMapper;
import top.zhx47.qxx.service.PlatformInfoService;

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
    public SystemInfoPO getSystemInfoPO() throws IllegalAccessException {
        List<Map<String, String>> systemInfoList = this.baseMapper.getSystemInfo();
        Map<String, String> systemInfoMap = systemInfoList.stream().collect(Collectors.toMap(map -> map.get("key"), item -> item.get("value")));
        SystemInfoPO systemInfoPO = new SystemInfoPO();
        Class<SystemInfoPO> systemInfoPOClass = SystemInfoPO.class;
        Field[] declaredFields = systemInfoPOClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            declaredField.set(systemInfoPO, systemInfoMap.get(humpToLine(declaredField.getName())));
        }
        return systemInfoPO;
    }

    @Override
    public void updateQDDURL(String url) {
        this.baseMapper.updateQDDURL(url);
    }

    @Override
    public AlipayInfoPO getAlipayInfoPO() throws IllegalAccessException {
        List<Map<String, String>> alipayInfoList = this.baseMapper.getAlipayInfo();
        Map<String, String> alipayInfoMap = alipayInfoList.stream().collect(Collectors.toMap(map -> map.get("key"), item -> item.get("value")));
        AlipayInfoPO alipayInfoPO = new AlipayInfoPO();
        Class<AlipayInfoPO> alipayInfoPOClass = AlipayInfoPO.class;
        Field[] declaredFields = alipayInfoPOClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            declaredField.set(alipayInfoPO, alipayInfoMap.get(this.humpToLine(declaredField.getName())));
        }
        return alipayInfoPO;
    }
}
