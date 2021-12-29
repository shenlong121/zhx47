package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 系统配置表
 * @Date: 2021/6/10 21:11
 */
@NoArgsConstructor
@Data
public class Config extends BaseEntity {

    @JSONField(serialize = false)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JSONField(name = "version")
    private String version;
    @JSONField(name = "versionApk")
    private String versionApk;
    @JSONField(name = "versionIos")
    private String versionIos;
    @JSONField(name = "checkVersion")
    private Integer checkVersion;
    @JSONField(name = "jiguang")
    private String jiguang;
    @JSONField(name = "juheKey")
    private String juheKey;
    @JSONField(name = "juhe")
    private String juhe;
    @JSONField(name = "domain")
    private String domain;

}
