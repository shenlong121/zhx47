package top.zhx47.qxx.api.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/28 22:33
 */
@NoArgsConstructor
@Data
public class SiteDTO {

    @JSONField(name = "siteId")
    private String siteId;
    @JSONField(name = "group")
    private String group;
    @JSONField(name = "name")
    private String name;

}
