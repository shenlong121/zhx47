package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/15 11:10
 */
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Integer updateUser;
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
