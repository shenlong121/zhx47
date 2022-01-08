package top.zhx47.qxx.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhx47.common.core.utils.AlipayUtils;
import top.zhx47.common.core.utils.ServletUtils;
import top.zhx47.qxx.datasource.entity.SysNotice;
import top.zhx47.qxx.datasource.entity.SysOrder;
import top.zhx47.qxx.mapper.SysNoticeMapper;
import top.zhx47.qxx.service.SysNoticeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
