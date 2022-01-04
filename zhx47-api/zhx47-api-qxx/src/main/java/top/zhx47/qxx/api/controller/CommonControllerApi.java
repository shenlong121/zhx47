package top.zhx47.qxx.api.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.web.R;
import top.zhx47.qxx.api.datasource.dto.UserDTO;

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
     * @Description: 获取版本信息
     * @Author: 张许
     * @Date: 2021/6/10 22:15
     */
    @RequestMapping(value = "/get_version", method = RequestMethod.POST)
    R getVersion() throws Exception;

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
