package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.datasource.entity.SysNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:17
 */
public interface SysNoticeService extends IService<SysNotice> {
    /**
     * 获取抢夕夕公告
     *
     * @return 抢夕夕公告
     */
    SysNotice getSysNotice();
}
