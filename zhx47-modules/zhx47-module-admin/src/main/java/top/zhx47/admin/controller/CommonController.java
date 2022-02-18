package top.zhx47.admin.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.admin.service.UserService;
import top.zhx47.api.admin.controller.CommonControllerApi;
import top.zhx47.common.core.web.R;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 张许
 * @Date: 2022/2/18 22:30
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController implements CommonControllerApi {

    private final UserService userService;

    @Override
    public R login(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Assert.hasText(username, "请填写账号");
        Assert.hasText(password, "请填写密码");
        // 生成令牌
        String token = userService.login(username, password);
        if (StringUtils.isEmpty(token)) {
            return R.ok("501", "手机号或者密码错误");
        }
        return R.ok(200, "登录成功").put("token", token);
    }
}
