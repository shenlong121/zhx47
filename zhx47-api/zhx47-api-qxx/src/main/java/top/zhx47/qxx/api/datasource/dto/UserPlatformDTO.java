package top.zhx47.qxx.api.datasource.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/9/22 20:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlatformDTO {

    @JSONField(name = "data")
    private List<DataDTO> data;
    @JSONField(name = "password")
    private String password;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JSONField(name = "id")
        private String id;
        @JSONField(name = "account")
        private String account;
        @JSONField(name = "password")
        private String password;
        @JSONField(name = "range")
        private Integer range;
    }
}
