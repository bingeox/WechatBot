package com.bingeox.wechatbot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/8
 **/
@Data
public class WeatherResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;
    /**
     * {
     *     "code": 1,
     *     "msg": "数据返回成功！",
     *     "data": {
     *         "address": "四川省 德阳市 罗江区",
     *         "cityCode": "510604",
     *         "temp": "15℃",
     *         "weather": "阴",
     *         "windDirection": "北",
     *         "windPower": "≤3级",
     *         "humidity": "91%",
     *         "reportTime": "2021-04-08 01:08:30"
     *     }
     * }
     */
    private String address;
    private String cityCode;
    private String temp;
    private String weather;
    private String windDirection;
    private String windPower;
    private String humidity;
    private String reportTime;

}
