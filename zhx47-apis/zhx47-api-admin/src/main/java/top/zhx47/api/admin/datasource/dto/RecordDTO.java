package top.zhx47.api.admin.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 获取订单的dto
 * @Date: 2021/8/28 21:12
 */
@NoArgsConstructor
@Data
public class RecordDTO {

    @JSONField(name = "beginTime")
    private String beginTime;
    @JSONField(name = "endTime")
    private String endTime;
    @JSONField(name = "keyword")
    private String keyword;
    @JSONField(name = "isReturn")
    private Integer isReturn;
}
