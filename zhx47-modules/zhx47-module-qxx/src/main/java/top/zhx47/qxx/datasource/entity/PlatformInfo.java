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
public class PlatformInfo extends BaseEntity {
    @TableId(value = "`key`", type = IdType.INPUT)
    private String key;
    @TableField(value = "`value`")
    private String value;
    @TableField(value = "`type`")
    private String type;
    @TableField(value = "`desc`")
    private String desc;
}
