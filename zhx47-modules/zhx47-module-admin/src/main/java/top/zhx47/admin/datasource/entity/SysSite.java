package top.zhx47.admin.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 平台表
 * @Date: 2021/6/10 21:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysSite extends BaseEntity {
    @JSONField(name = "siteName")
    private String siteName;
    @TableId(value = "site_id", type = IdType.INPUT)
    @JSONField(name = "siteId")
    private String siteId;
    @JSONField(name = "isFree")
    private Integer isFree;
    @JSONField(name = "isShow")
    private Integer isShow;
    @JSONField(name = "isLog")
    private Integer isLog;
    @JSONField(name = "weight")
    private Integer weight;
    @JSONField(name = "isHold")
    private Integer isHold;
    @JSONField(name = "isAndroid")
    private Integer isAndroid;
    @JSONField(name = "isIos")
    private Integer isIos;
    @JSONField(name = "isRankShow")
    private Integer isRankShow;
    @JSONField(name = "isRankSearch")
    private Integer isRankSearch;
    @JSONField(name = "isHide")
    private Integer isHide;
}
