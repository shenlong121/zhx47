package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: 张许
 * @Description: 礼品表格
 * @Date: 2022/1/15 16:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysProduct extends BaseEntity {
    /**
     * 礼品ID
     */
    @JSONField(name = "recordId")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 礼品名称
     */
    private String title;

    /**
     * 礼品图片
     */
    private String image;

    /**
     * 完全不知道干嘛用的
     */
    private Integer isCode;

    /**
     * 剩余个数--（前端显示*100）
     */
    private Integer num;

    /**
     * 完全不知道干嘛用的
     */
    private Integer origin;

    /**
     * 兑换所需积分
     */
    private Integer points;

    /**
     * 原价
     */
    private Integer price;

    /**
     * 完全不知道干嘛用的
     */
    private Integer sales;

    /**
     * 礼品描述
     */
    private String description;

    @JSONField(name = "addTime")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}

