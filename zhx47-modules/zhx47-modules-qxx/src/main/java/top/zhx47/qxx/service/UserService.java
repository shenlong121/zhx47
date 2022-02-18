package top.zhx47.qxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.qxx.api.datasource.dto.PageDTO;
import top.zhx47.qxx.api.datasource.dto.ReceiveInfoDTO;
import top.zhx47.qxx.api.datasource.dto.UserDTO;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.datasource.entity.UserSiteCollect;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/10 22:36
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     */
    Boolean registerUser(UserDTO registerOrResetDTO) throws Exception;

    User getUser();

    UserSiteCollect addCollect(String siteId, String type);

    User getUserByPhone(String phone);

    String login(String phone, String password);

    Long countFriend();

    List<User> getFriendListByPage(PageDTO pageDTO);

    boolean payCode(String code);

    User loadUserByPhone(String phone);

    /**
     * 获取当前用户的礼品收获信息
     */
    List<ReceiveInfoDTO> getAddress();

    /**
     * 更新用户收获信息
     */
    void addAddress(ReceiveInfoDTO receiveInfoDTO);
}
