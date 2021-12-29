package top.zhx47.qxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhx47.qxx.datasource.entity.Notice;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:17
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    public Notice getNotice();
}
