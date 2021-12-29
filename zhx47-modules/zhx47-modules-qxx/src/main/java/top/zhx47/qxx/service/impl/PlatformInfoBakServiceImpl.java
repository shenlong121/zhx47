package top.zhx47.qxx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.api.datasource.dto.UserPlatformDTO;
import top.zhx47.qxx.datasource.entity.PlatformInfoBak;
import top.zhx47.qxx.mapper.PlatformInfoBakMapper;
import top.zhx47.qxx.service.PlatformInfoBakService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/9/22 20:24
 */
@Service
public class PlatformInfoBakServiceImpl extends ServiceImpl<PlatformInfoBakMapper, PlatformInfoBak> implements PlatformInfoBakService {
    @Override
    @Transactional
    public void update(UserPlatformDTO userPlatformDTO) {
        PlatformInfoBak platformInfoBak = new PlatformInfoBak(SecurityUtils.getId(), JSONObject.toJSONString(userPlatformDTO.getData()), userPlatformDTO.getPassword());
        this.saveOrUpdate(platformInfoBak);
    }

    @Override
    public String queryByUserId(String password) {
        PlatformInfoBak platformInfoBak = this.getById(SecurityUtils.getId());
        if (platformInfoBak != null && platformInfoBak.getPassword().equals(password)) {
            return platformInfoBak.getInfo();
        }
        return null;
    }
}
