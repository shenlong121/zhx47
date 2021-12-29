package top.zhx47.qxx.api.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 获取验证码
 * @Date: 2021/6/10 23:11
 */
@NoArgsConstructor
@Data
public class VerificationCodeDTO {

    /**
     * 手机号
     */
    @JSONField(name = "phone")
    private String phone;
    /**
     * 用于判断是注册还是忘记密码
     */
    @JSONField(name = "origin")
    private String origin;
}
