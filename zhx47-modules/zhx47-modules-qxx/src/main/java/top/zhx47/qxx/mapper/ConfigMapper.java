package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.qxx.datasource.entity.Config;

/**
 * @Author: 张许
 * @Description: 配置表持久层
 * @Date: 2021/6/10 22:02
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    public Config getConfig();
}
