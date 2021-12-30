package top.zhx47.qxx.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSiteBackup extends BaseEntity {
    @TableId(value = "`user_id`", type = IdType.INPUT)
    private Integer userId;
    @TableField(value = "`info`")
    private String info;
    @TableField(value = "`password`")
    private String password;
}
