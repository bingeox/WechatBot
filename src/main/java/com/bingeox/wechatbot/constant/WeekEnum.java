package com.bingeox.wechatbot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/7
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum WeekEnum {

    MONDAY(1, "星期一"),
    TUESDAY(2, "星期二"),
    WEDNESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FRIDAY(5, "星期五"),
    SATURDAY(6, "星期六"),
    SUNDAY(7, "星期日");
    private int code;
    private String des;

    public static String getDayByCode(int code) {
        for (WeekEnum value : WeekEnum.values()) {
            if (code == value.code) {
                return value.des;
            }
        }
        return "";
    }
}
