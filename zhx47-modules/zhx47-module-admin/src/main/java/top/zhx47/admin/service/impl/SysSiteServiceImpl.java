package top.zhx47.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.admin.datasource.entity.SysSite;
import top.zhx47.admin.mapper.SysSiteMapper;
import top.zhx47.admin.service.SysSiteService;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:11
 */
@Service
public class SysSiteServiceImpl extends ServiceImpl<SysSiteMapper, SysSite> implements SysSiteService {

    @Override
    public List<SysSite> getSysSiteList() {
        return baseMapper.getSysSiteList();
    }
}
