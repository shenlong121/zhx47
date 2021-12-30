package top.zhx47.qxx.api.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/18 23:57
 */
@NoArgsConstructor
@Data
public class SaveOrdersDTO {
    @JSONField(name = "orders")
    private List<String> orders;
}
