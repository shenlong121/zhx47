package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: 张许
 * @Description: 用户表
 * @Date: 2021/6/10 21:19
 */
@NoArgsConstructor
@Data
public class User extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @JSONField(name = "phone")
    private String phone;
    @JsonIgnore
    @JSONField(serialize = false)
    private String password;
    @JSONField(name = "expireTime")
    private LocalDate expireTime;
    @JSONField(name = "bonus")
    private Integer bonus;
    @JSONField(name = "recordId")
    private String recordId;
    @JSONField(name = "isWithdraw")
    private Integer isWithdraw;
    @JSONField(name = "isAgent")
    private Integer isAgent;
    @JSONField(name = "role")
    private String role;
    @JSONField(name = "points")
    private Integer points;
    @JSONField(name = "isCode")
    private Integer isCode;
    @JsonIgnore
    @JSONField(serialize = false)
    private String parendId;

}
