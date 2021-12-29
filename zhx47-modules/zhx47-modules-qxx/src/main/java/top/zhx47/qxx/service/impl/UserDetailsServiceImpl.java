package top.zhx47.qxx.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.service.UserService;

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
//        User user = baseMapper.loadUserByPhone(phone);
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        BeanUtils.copyProperties(user, userDetailsDTO);
        return userDetailsDTO;
    }
}
