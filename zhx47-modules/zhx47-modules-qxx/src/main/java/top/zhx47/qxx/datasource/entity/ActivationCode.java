package top.zhx47.qxx.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivationCode extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String code;
    private Integer day;
    private Integer enable;
}
