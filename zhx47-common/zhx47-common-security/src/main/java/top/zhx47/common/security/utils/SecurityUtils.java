package top.zhx47.common.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.zhx47.common.core.constant.HttpStatus;
import top.zhx47.common.core.exception.CustomException;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {

    public static Integer getId() {
        try {
            return getLoginUser().getId();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static UserDetailsDTO getLoginUser() {
        try {
            Object principal = getAuthentication().getPrincipal();
            if (principal instanceof String && "anonymousUser".equals(principal.toString())) {
                UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
                userDetailsDTO.setId(-1);
                return userDetailsDTO;
            }
            return (UserDetailsDTO) principal;
        } catch (Exception e) {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
