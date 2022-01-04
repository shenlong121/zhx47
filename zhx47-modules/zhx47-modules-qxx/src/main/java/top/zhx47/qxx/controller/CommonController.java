package top.zhx47.qxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.constant.BusinessConstants;
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

    @Override
    @RequestMapping(value = "/get_version", method = RequestMethod.POST)
    public R getVersion() {
        return R.ok().putBodyByMap("config", sysConfigService.getSysConfig());
    }

    @RequestMapping(value = {"/get_reply", "/get_reply_recent"}, method = RequestMethod.POST)
    public R reply() {
        return R.ok();
    }

    @RequestMapping(value = "/get_code", method = RequestMethod.POST)
    public R getCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        // 数据校验
        if (StringUtils.isEmpty(verificationCodeDTO.getOrigin()) || StringUtils.isEmpty(verificationCodeDTO.getPhone())) {
            return R.error(BusinessConstants.INVALIDDATA, "非法数据");
        }
        User user = userService.loadUserByPhone(verificationCodeDTO.getPhone());
        if ("register".equals(verificationCodeDTO.getOrigin())) {
            if (user == null) {
                return R.ok(200, "验证码获取成功");
            } else {
                return R.error(BusinessConstants.REGISTERED, "手机号已注册");
            }
        } else if ("reset".equals(verificationCodeDTO.getOrigin())) {
            return R.error(BusinessConstants.UNREGISTERED, "重置密码请向上级申请！");
        }
        return R.error(BusinessConstants.REGISTERED, "请检查手机号");
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public R reset() {
        return R.error(BusinessConstants.INVALIDDATA, "重置密码请向上级申请！");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public R register(@RequestBody UserDTO registerOrResetDTO) throws Exception {
        // 数据校验
        if (StringUtils.isEmpty(registerOrResetDTO.getPassword()) || StringUtils.isEmpty(registerOrResetDTO.getPhone()) || StringUtils.isEmpty(registerOrResetDTO.getRecomender())) {
            return R.error(BusinessConstants.INVALIDDATA, "非法数据");
        }
        Boolean flag = this.userService.registerUser(registerOrResetDTO);
        if (flag) {
            return R.ok(200, "注册成功");
        }
        return R.error(BusinessConstants.REGISTERED, "邀请码错误或已注册或频繁请求！");
    }

    @RequestMapping(value = "/get_notice", method = RequestMethod.POST)
    public R getNotice(@RequestBody JSONObject jsonObject) {
        SysNotice sysNotice = sysNoticeService.getSysNotice();
        if (!"login".equals(jsonObject.getString("name"))) {
            sysNotice.setContent("有问题请联系上级进行反馈！！");
        }
        return R.ok().putBodyByMap("notice", sysNotice);
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        // 生成令牌
        String token = userService.login(userDTO.getPhone(), userDTO.getPassword());
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
