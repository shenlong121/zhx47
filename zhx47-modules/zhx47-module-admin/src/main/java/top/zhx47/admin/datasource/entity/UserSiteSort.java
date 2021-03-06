package top.zhx47.admin.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 用户平台排序
 * @Date: 2021/6/10 21:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSiteSort extends BaseEntity {
    @TableId(value = "user_id", type = IdType.INPUT)
    @JSONField(serialize = false)
    private String userId;
    @TableField(value = "`order`")
    private String order;
}
