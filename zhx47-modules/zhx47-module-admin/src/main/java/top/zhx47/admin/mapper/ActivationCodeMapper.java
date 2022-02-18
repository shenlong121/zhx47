package top.zhx47.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.admin.datasource.entity.ActivationCode;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/29 19:48
 */
@Mapper
public interface ActivationCodeMapper extends BaseMapper<ActivationCode> {
    ActivationCode queryByCode(String code);
}
