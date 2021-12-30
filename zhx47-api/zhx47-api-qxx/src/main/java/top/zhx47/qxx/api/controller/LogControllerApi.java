package top.zhx47.qxx.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.web.R;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/28 22:27
 */
@RestController
@RequestMapping("/log")
public interface LogControllerApi {
    /**
     * @Description: 添加日志
     * @Author: 张许
     * @Date: 2021/6/10 22:15
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    R add();
}
