package top.zhx47.common.core.exception.user;

/**
 * 用户密码不正确异常类
 *
 * @author zhx47
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}