package top.zhx47.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.admin.datasource.entity.ActivationCode;
import top.zhx47.admin.mapper.ActivationCodeMapper;
import top.zhx47.admin.service.ActivationCodeService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/29 19:47
 */
@Service
public class ActivationCodeServiceImpl extends ServiceImpl<ActivationCodeMapper, ActivationCode> implements ActivationCodeService {
    @Override
    public ActivationCode queryByCode(String code) {
        return baseMapper.queryByCode(code);
    }
}
