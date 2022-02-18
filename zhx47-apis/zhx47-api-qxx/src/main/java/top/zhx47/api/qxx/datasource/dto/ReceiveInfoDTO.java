package top.zhx47.api.qxx.datasource.dto;

import lombok.Data;

/**
 * @Author: 张许
 * @Description: 用户收获信息
 * @Date: 2022/1/31 22:35
 */
@Data
public class ReceiveInfoDTO {

    /**
     * 礼品收货人姓名
     */
    private String name;

    /**
     * 礼品收货人联系方式
     */
    private String cell;

    /**
     * 礼品收货人地址
     */
    private String address;
}
