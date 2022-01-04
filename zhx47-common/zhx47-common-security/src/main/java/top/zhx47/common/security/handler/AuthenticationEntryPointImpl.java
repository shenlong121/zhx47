package top.zhx47.common.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.zhx47.common.core.constant.BusinessConstants;
import top.zhx47.common.core.utils.ServletUtils;
import top.zhx47.common.core.web.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        ServletUtils.renderString(response, JSON.toJSONString(R.error(BusinessConstants.NO_LOGIN, "凭证过期，请重新登录")));
    }
}
