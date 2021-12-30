package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.datasource.entity.SysNotice;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:17
 */
public interface SysNoticeService extends IService<SysNotice> {
    /**
     * @Description:
     * @Author: 张许
     * @Date: 2021/6/12 1:51
     * @Return top.zhx47.qxx.datasource.entity.Notice
     * @Exception
     */
    public SysNotice getSysNotice();
}
