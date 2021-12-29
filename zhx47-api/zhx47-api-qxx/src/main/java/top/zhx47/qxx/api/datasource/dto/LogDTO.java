package top.zhx47.qxx.api.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: 张许
 * @Description: 日志
 * @Date: 2021/6/11 23:29
 */
@NoArgsConstructor
@Data
public class LogDTO {
    @JSONField(name = "timestamp")
    private Date timestamp;
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "error")
    private String error;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "path")
    private String path;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "siteId")
    private String siteId;
    @JSONField(name = "request")
    private String request;
    @JSONField(name = "response")
    private String response;
    @JSONField(name = "isSuccess")
    private Integer isSuccess;
    @JSONField(name = "device")
    private String device;
}
