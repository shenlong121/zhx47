package top.zhx47.admin.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: 张许
 * @Description: 用户表
 * @Date: 2021/6/10 21:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(serialize = false)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 会员过期时间
     */
    private LocalDate expireTime;

    /**
     * 余额
     */
    private Integer bonus;

    /**
     * 邀请码
     */
    private String recordId;

    /**
     * 完全不知道干嘛的
     */
    private Integer isWithdraw;

    /**
     * 是否是代理 0-不是 1-是
     */
    private Integer isAgent;

    /**
     * 会员等级
     */
    private String role;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 完全不知道干嘛的
     */
    private Integer isCode;

    /**
     * 邀请人ID
     */
    @JSONField(serialize = false)
    private String parendId;

    /**
     * 礼品收货人姓名
     */
    @JSONField(serialize = false)
    private String name;

    /**
     * 礼品收货人联系方式
     */
    @JSONField(serialize = false)
    private String cell;

    /**
     * 礼品收货人地址
     */
    @JSONField(serialize = false)
    private String address;

}
