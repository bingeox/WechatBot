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
@NoArgsConstructor
@AllArgsConstructor
public enum ConstellationEnum {
    
    ARIES("白羊座", "aries"),
    TAURUS("金牛座", "taurus"),
    GEMINI("双子座", "gemini"),
    CANCER("巨蟹座", "cancer"),
    LEO("狮子座", "leo"),
    VIRGO("处女座", "virgo"),
    LIBRA("天秤座", "libra"),
    SCORPIO("天蝎座", "scorpio"),
    SAGITTARIUS("射手座", "sagittarius"),
    CAPRICORN("摩羯座", "capricorn"),
    AQUARIUS("水瓶座", "aquarius"),
    PISCES("双鱼座", "pisces");
    private String name;
    private String code;
}
