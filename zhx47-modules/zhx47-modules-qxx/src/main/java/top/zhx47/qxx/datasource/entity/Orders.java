package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 用户平台排序
 * @Date: 2021/6/10 21:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders extends BaseEntity {
    @TableId(value = "user_id", type = IdType.INPUT)
    @JSONField(serialize = false)
    private String userId;
    @TableField(value = "`order`")
    private String order;
}
