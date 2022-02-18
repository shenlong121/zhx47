package top.zhx47.api.qxx.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/15 0:26
 */
@NoArgsConstructor
@Data
public class UserDTO {
    /**
     * 手机号
     */
    @JSONField(name = "phone")
    private String phone;
    /**
     * 密码
     */
    @JSONField(name = "password")
    private String password;
    /**
     * 验证码
     */
    @JSONField(name = "code")
    private String code;
    /**
     * 邀请码（注册时候才有）
     */
    @JSONField(name = "recomender")
    private String recomender;
}
