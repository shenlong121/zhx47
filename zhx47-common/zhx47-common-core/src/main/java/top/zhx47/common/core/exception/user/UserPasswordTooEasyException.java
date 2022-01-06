package top.zhx47.common.core.exception.user;

/**
 * 用户密码不符合规范异常类
 *
 * @author zhx47
 */
public class UserPasswordTooEasyException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordTooEasyException() {
        super("user.password.too.easy", null);
    }
}