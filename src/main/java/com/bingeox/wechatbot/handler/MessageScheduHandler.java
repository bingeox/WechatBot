package com.bingeox.wechatbot.handler;

import com.bingeox.wechatbot.control.calendar.RtCalendar;
import com.bingeox.wechatbot.control.horoscope.Horoscope;
import com.bingeox.wechatbot.control.weather.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xiaobing@meicai.cn
 * @description 定时任务
 * @since 2021/4/9
 **/
@Slf4j
@Service
public class MessageScheduHandler {

    @Autowired
    private WeChatMessageHandler handler;
    @Autowired
    private RtCalendar rtCalendar;
    @Autowired
    private Horoscope horoscope;
    @Autowired
    private Weather weather;

    @Value("${special.wxid}")
    private String specialWxId;



}
