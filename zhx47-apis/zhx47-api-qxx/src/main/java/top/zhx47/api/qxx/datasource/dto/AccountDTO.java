package top.zhx47.api.qxx.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/29 0:01
 */
@NoArgsConstructor
@Data
public class AccountDTO {

    @JSONField(name = "recordId")
    private Long recordId;
    @JSONField(name = "logTime")
    private String logTime;
    @JSONField(name = "site")
    private String site;
    @JSONField(name = "earned")
    private String earned;
    @JSONField(name = "advanced")
    private String advanced;
    @JSONField(name = "platphom")
    private String platphom;
    @JSONField(name = "shop")
    private String shop;
    @JSONField(name = "remark")
    private String remark;
    @JSONField(name = "isReturn")
    private String isReturn;
    @JSONField(name = "isEarned")
    private String isEarned;
    @JSONField(name = "buyer")
    private String buyer;
    @JSONField(name = "money")
    private String money;
    @JSONField(name = "ftype")
    private String ftype;
}
