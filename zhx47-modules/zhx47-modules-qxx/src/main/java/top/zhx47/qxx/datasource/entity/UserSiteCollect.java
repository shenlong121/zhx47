package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 用户平台表
 * @Date: 2021/6/10 21:24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSiteCollect extends BaseEntity {
    @JsonIgnore
    @JSONField(serialize = false)
    private String userId;
    @JSONField(name = "siteId")
    private String siteId;
    @JSONField(name = "group")
    @TableField(value = "`group`")
    private String group;
    @JSONField(name = "index")
    @TableField(value = "`index`")
    private String index;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "collectId")
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;
}
