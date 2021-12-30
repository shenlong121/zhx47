package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.SysConfig;
import top.zhx47.qxx.mapper.SysConfigMapper;
import top.zhx47.qxx.service.SysConfigService;

/**
 * @Author: 张许
 * @Description: 配置表业务层实现
 * @Date: 2021/6/10 22:06
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Override
    public SysConfig getSysConfig() {
        return baseMapper.getSysConfig();
    }
}
