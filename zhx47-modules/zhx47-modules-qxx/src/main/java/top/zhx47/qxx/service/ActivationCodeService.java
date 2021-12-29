package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.datasource.entity.ActivationCode;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/29 19:46
 */
public interface ActivationCodeService extends IService<ActivationCode> {
    ActivationCode queryByCode(String code);
}
