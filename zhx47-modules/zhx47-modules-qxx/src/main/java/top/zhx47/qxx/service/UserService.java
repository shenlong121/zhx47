package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.api.datasource.dto.PageDTO;
import top.zhx47.qxx.api.datasource.dto.UserDTO;
import top.zhx47.qxx.datasource.entity.Collect;
import top.zhx47.qxx.datasource.entity.User;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/10 22:36
 */
public interface UserService extends IService<User> {
    /**
     * @param registerOrResetDTO
     * @Description: 用户注册
     * @Author: 张许
     * @Date: 2021/6/11 0:45
     * @Return void
     * @Exception
     */
    public Boolean registerUser(UserDTO registerOrResetDTO) throws Exception;

    User getUser();

    Collect addCollect(String siteId, String type);

    User getUserByPhone(String phone);

    String login(String phone, String password);

    Long countFriend();

    List<User> getFriendListByPage(PageDTO pageDTO);

    boolean payCode(String code);

    User loadUserByPhone(String phone);
}
