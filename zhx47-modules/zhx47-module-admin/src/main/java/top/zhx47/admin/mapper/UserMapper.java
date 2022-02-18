package top.zhx47.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zhx47.admin.datasource.entity.User;
import top.zhx47.api.admin.datasource.dto.ReceiveInfoDTO;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/10 22:35
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User loadUserByPhone(String phone);

    Long countFriend(Integer parentId);

    List<User> getFriendListByPage(@Param("parentId") Integer parentId, @Param("pageIndex") int pageIndex, @Param("pageNum") Integer pageNum);

    ReceiveInfoDTO getAddress(Integer id);

    void addAddress(@Param("receiveInfo") ReceiveInfoDTO receiveInfoDTO, @Param("id") Integer id);
}
