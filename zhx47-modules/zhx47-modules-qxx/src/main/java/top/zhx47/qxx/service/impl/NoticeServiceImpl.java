package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.Notice;
import top.zhx47.qxx.mapper.NoticeMapper;
import top.zhx47.qxx.service.NoticeService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:18
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public Notice getNotice() {
        return baseMapper.getNotice();
    }
}
