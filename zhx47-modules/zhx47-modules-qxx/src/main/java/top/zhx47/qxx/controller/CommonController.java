package top.zhx47.qxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.constant.BusinessConstants;
import top.zhx47.common.core.constant.Constants;
import top.zhx47.common.core.exception.user.UserPasswordTooEasyException;
import top.zhx47.common.core.utils.AlipayUtils;
import top.zhx47.common.core.utils.ServletUtils;
import top.zhx47.common.core.web.R;
import top.zhx47.qxx.api.controller.CommonControllerApi;
import top.zhx47.qxx.api.datasource.dto.UserDTO;
import top.zhx47.qxx.api.datasource.dto.VerificationCodeDTO;
import top.zhx47.qxx.datasource.entity.SysNotice;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.datasource.po.AlipayInfoPO;
import top.zhx47.qxx.service.SysConfigService;
import top.zhx47.qxx.service.SysNoticeService;
import top.zhx47.qxx.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/15 0:01
 */
@RestController
@RequestMapping("/common")
public class CommonController implements CommonControllerApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private AlipayInfoPO alipayInfoPO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public R getVersion() {
        return R.ok().putBodyByMap("config", sysConfigService.getSysConfig());
    }

    @Override
    public R reply() {
        return R.ok();
    }

    @Override
    public R getCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        // 数据校验
        if (StringUtils.isEmpty(verificationCodeDTO.getOrigin()) || StringUtils.isEmpty(verificationCodeDTO.getPhone())) {
            return R.error(BusinessConstants.INVALIDDATA, "非法数据");
        }
        User user = userService.loadUserByPhone(verificationCodeDTO.getPhone());
        if (user == null) {
            return R.ok(200, "验证码获取成功");
        }
        if ("register".equals(verificationCodeDTO.getOrigin())) {
            return R.error(BusinessConstants.REGISTERED, "手机号已注册");
        } else if ("reset".equals(verificationCodeDTO.getOrigin())) {
            return R.error(BusinessConstants.UNREGISTERED, "请输入正确的手机号");
        }
        return R.error(BusinessConstants.INVALIDDATA, "非法请求");
    }

    @Override
    public R reset(@RequestBody UserDTO registerOrResetDTO) {
        User user = this.userService.loadUserByPhone(registerOrResetDTO.getPhone());
        if (user != null && !registerOrResetDTO.getPassword().matches(Constants.PASSWORD_REGEX)) {
            String encode = this.passwordEncoder.encode("123456789");
            if (encode.equals(user.getPassword())) {
                LOGGER.info("用户： {} 更改密码", registerOrResetDTO.getPhone());
                String newPasswd = this.passwordEncoder.encode(registerOrResetDTO.getPassword());
                user.setPassword(newPasswd);
                this.userService.updateById(user);
                return R.ok(200, "密码重置成功");
            }
        }
        return R.error(BusinessConstants.INVALIDDATA, "重置密码请向上级申请！");
    }

    @Override
    public R register(@RequestBody UserDTO registerOrResetDTO) throws Exception {
        // 数据校验
        if (StringUtils.isEmpty(registerOrResetDTO.getPassword()) || StringUtils.isEmpty(registerOrResetDTO.getPhone()) || StringUtils.isEmpty(registerOrResetDTO.getRecomender())) {
            return R.error(BusinessConstants.INVALIDDATA, "非法数据");
        }
        if (!registerOrResetDTO.getPassword().matches(Constants.PASSWORD_REGEX)) {
            return R.error(BusinessConstants.INVALIDDATA, "请录入 6-20 位密码，并且同时包含字母和数字！");
        }
        Boolean flag = this.userService.registerUser(registerOrResetDTO);
        if (flag) {
            return R.ok(200, "注册成功");
        }
        return R.error(BusinessConstants.REGISTERED, "邀请码错误或已注册或频繁请求！");
    }

    @Override
    public R getNotice(@RequestBody JSONObject jsonObject) {
        SysNotice sysNotice = sysNoticeService.getSysNotice();
        if (!"login".equals(jsonObject.getString("name"))) {
            sysNotice.setContent("有问题请联系上级进行反馈！！");
        }
        return R.ok().putBodyByMap("notice", sysNotice);
    }

    @Override
    public R login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        // 生成令牌
        String token = userService.login(userDTO.getPhone(), userDTO.getPassword());
        // 之前没考虑，数据库中已经存在简单密码的情况，为了区分提示，所以校验放到后面了，简单密码需要去修改密码
        if (!userDTO.getPassword().matches(Constants.PASSWORD_REGEX)) {
            throw new UserPasswordTooEasyException();
        }
        response.setContentType("application/json;charset=utf-8");
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(84000);
        cookie.setPath("/");
        response.addCookie(cookie);
        Map<String, Object> result = new HashMap<>();
        result.put("userType", 1);
        result.put("token", token);
        return R.ok(200, "登录成功").putBodyByObject(result);
    }

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        Map<String, String> param = new HashMap<>();
        for (Map.Entry<String, String[]> entry : entries) {
            param.put(entry.getKey(), String.join("", entry.getValue()));
        }
        try {
            boolean alipayRSACheckedV2 = AlipayUtils.signVerification(param, this.alipayInfoPO.getAlipayPublicKey());
            if (!alipayRSACheckedV2) {
                ServletUtils.renderText(response, "fail");
            }
            //  TODO 其他业务校验
        } catch (AlipayApiException e) {
            ServletUtils.renderText(response, "fail");
        }
        ServletUtils.renderText(response, "success");
    }
}
