package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.ActivationCode;
import top.zhx47.qxx.mapper.ActivationCodeMapper;
import top.zhx47.qxx.service.ActivationCodeService;

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
