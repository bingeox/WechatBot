package com.bingeox.wechatbot.control.horoscope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description 爬取 星座屋 星座运势
 * https://www.xzw.com/
 * @since 2021/4/7
 **/
@Slf4j
@Component
public class Horoscope {
    private static final String BASE_URL = "https://www.xzw.com/fortune/";
    private static final String URL_TOMORROW = "https://www.xzw.com/fortune/{}/1.html";

    public void getXzwHoroscope(String name, boolean isTomorrow) {

    }
}

