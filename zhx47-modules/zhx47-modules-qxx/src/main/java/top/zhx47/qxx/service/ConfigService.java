package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.datasource.entity.Config;

/**
 * @Author: 张许
 * @Description: 配置表业务层接口
 * @Date: 2021/6/10 22:04
 */
public interface ConfigService extends IService<Config> {
    public Config getConfig();
}
