package top.zhx47.api.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.web.R;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 张许
 * @Date: 2022/2/18 22:23
 * @Description:
 */
@RestController
@RequestMapping("/common")
public interface CommonControllerApi {

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    R login(@RequestBody JSONObject jsonObject, HttpServletResponse response) throws Exception;

}
