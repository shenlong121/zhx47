package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.Site;
import top.zhx47.qxx.mapper.SiteMapper;
import top.zhx47.qxx.service.SiteService;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:11
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {

    @Override
    public List<Site> getSiteList() {
        return baseMapper.getSiteList();
    }
}
