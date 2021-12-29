package top.zhx47.qxx.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import top.zhx47.common.security.utils.SecurityUtils;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Authentication authentication = SecurityUtils.getAuthentication();
        setFieldValByName("createUser", authentication == null ? -1 : SecurityUtils.getId(), metaObject);
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Authentication authentication = SecurityUtils.getAuthentication();
        setFieldValByName("updateUser", authentication == null ? -1 : SecurityUtils.getId(), metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}