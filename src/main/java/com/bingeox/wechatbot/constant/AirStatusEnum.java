package com.bingeox.wechatbot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description 空气质量
 * @since 2021/3/30
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AirStatusEnum {

    SUPER(50,"优"),
    GOOD(100,"良"),
    LIGHT(150,"轻度污染"),
    MODERATE(200,"中度污染"),
    HEAVY(300,"重度污染"),
    SERIOUS(3000,"严重污染");

    private int code;
    private String status;
}
