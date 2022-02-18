package top.zhx47.admin.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/15 11:10
 */
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(serialize = false)
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;
    @JSONField(serialize = false)
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Integer updateUser;
    @JSONField(serialize = false)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JSONField(serialize = false)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
