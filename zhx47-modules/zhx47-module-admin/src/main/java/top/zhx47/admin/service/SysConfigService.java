package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.SysConfig;

/**
 * @Author: 张许
 * @Description: 配置表业务层接口
 * @Date: 2021/6/10 22:04
 */
public interface SysConfigService extends IService<SysConfig> {
    SysConfig getSysConfig();
}
