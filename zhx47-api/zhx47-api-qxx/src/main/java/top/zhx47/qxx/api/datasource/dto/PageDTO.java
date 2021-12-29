package top.zhx47.qxx.api.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description: 分页属性
 * @Date: 2021/8/28 21:28
 */
@NoArgsConstructor
@Data
public class PageDTO {

    @JSONField(name = "pageIndex")
    private Integer pageIndex;
    @JSONField(name = "pageNum")
    private Integer pageNum;

}
