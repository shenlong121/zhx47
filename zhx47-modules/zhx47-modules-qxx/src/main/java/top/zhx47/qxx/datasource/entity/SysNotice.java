package top.zhx47.qxx.datasource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 通知表
 * @Date: 2021/6/10 21:21
 */
@NoArgsConstructor
@Data
public class SysNotice extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id", serialize = false)
    private Long id;
    @JSONField(name = "isOn")
    private Integer isOn;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "content")
    private String content;

}
