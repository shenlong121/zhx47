package top.zhx47.common.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.zhx47.common.core.constant.HttpStatus;
import top.zhx47.common.core.utils.ServletUtils;
import top.zhx47.common.core.utils.StringUtils;
import top.zhx47.common.core.web.R;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.common.security.service.TokenService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author ruoyi
 */

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        UserDetailsDTO loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getPhone());
            // 记录用户退出日志
        }
        ServletUtils.renderString(response, JSON.toJSONString(R.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
