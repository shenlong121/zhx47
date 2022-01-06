package top.zhx47.qxx.api.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.web.R;
import top.zhx47.qxx.api.datasource.dto.UserDTO;
import top.zhx47.qxx.api.datasource.dto.VerificationCodeDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/15 0:57
 */
@RestController
@RequestMapping("/common")
public interface CommonControllerApi {
    /**
     * 获取版本信息
     */
    @RequestMapping(value = "/get_version", method = RequestMethod.POST)
    R getVersion() throws Exception;

    /**
     * 心跳接口
     */
    @RequestMapping(value = {"/get_reply", "/get_reply_recent"}, method = RequestMethod.POST)
    R reply();

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/get_code", method = RequestMethod.POST)
    R getCode(@RequestBody VerificationCodeDTO verificationCodeDTO);

    /**
     * 重置密码
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    R reset(@RequestBody UserDTO registerOrResetDTO);

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    R register(@RequestBody UserDTO registerOrResetDTO) throws Exception;

    /**
     * 获取通知
     */
    @RequestMapping(value = "/get_notice", method = RequestMethod.POST)
    R getNotice(@RequestBody JSONObject jsonObject);

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    R login(@RequestBody UserDTO userDTO, HttpServletResponse response) throws Exception;

    /**
     * 支付宝回调通知支付结果
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    void notify(HttpServletRequest request, HttpServletResponse response);
}
