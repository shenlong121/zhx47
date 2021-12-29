package top.zhx47.qxx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.common.core.web.R;
import top.zhx47.qxx.api.controller.LogControllerApi;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/28 22:27
 */
@RestController
@RequestMapping("/log")
public class LogController implements LogControllerApi {
    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R add() throws Exception {
        // 垃圾抢多多，偷偷保存用户数据
        return R.ok();
    }
}
