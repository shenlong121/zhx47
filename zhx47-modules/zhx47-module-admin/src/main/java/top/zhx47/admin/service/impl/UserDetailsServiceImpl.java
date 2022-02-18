package top.zhx47.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.admin.datasource.entity.User;
import top.zhx47.admin.service.UserService;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/28 0:28
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        if (user.getId() != 1) {
            throw new UsernameNotFoundException("该用户非管理员!");
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        BeanUtils.copyProperties(user, userDetailsDTO);
        return userDetailsDTO;
    }
}
