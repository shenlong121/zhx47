package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.User;
import top.zhx47.admin.datasource.entity.UserSiteCollect;
import top.zhx47.api.admin.datasource.dto.PageDTO;
import top.zhx47.api.admin.datasource.dto.ReceiveInfoDTO;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/10 22:36
 */
public interface UserService extends IService<User> {

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
