package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.qxx.datasource.entity.SysNotice;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:17
 */
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {
    SysNotice getSysNotice();
}
