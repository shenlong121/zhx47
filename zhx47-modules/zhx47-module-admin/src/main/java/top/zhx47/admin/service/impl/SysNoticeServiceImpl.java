package top.zhx47.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.admin.datasource.entity.SysNotice;
import top.zhx47.admin.mapper.SysNoticeMapper;
import top.zhx47.admin.service.SysNoticeService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:18
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

    @Override
    public SysNotice getSysNotice() {
        return baseMapper.getSysNotice();
    }
}
