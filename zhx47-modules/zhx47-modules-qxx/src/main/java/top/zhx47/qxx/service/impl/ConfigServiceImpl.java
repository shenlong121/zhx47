package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.Config;
import top.zhx47.qxx.mapper.ConfigMapper;
import top.zhx47.qxx.service.ConfigService;

/**
 * @Author: 张许
 * @Description: 配置表业务层实现
 * @Date: 2021/6/10 22:06
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Override
    public Config getConfig() {
        return baseMapper.getConfig();
    }
}
