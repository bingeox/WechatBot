package com.bingeox.wechatbot.handler;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.StrUtil;
import com.bingeox.wechatbot.config.WeChatBotConfig;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.control.bot.RobotFactory;
import com.bingeox.wechatbot.control.calendar.RtCalendar;
import com.bingeox.wechatbot.control.onewords.Caihongpi;
import com.bingeox.wechatbot.control.onewords.OnewordFactory;
import com.bingeox.wechatbot.control.weather.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
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
    private Weather weather;
    @Autowired
    private OnewordFactory oneword;

    /**
     * 每天早上 7:30 执行一次
     */
    @Scheduled(cron = "0 30 7 * * ?")
    public void alarmMessage() {
        //计算时间差值
        long between = DateTime.of(Constants.START_DATE, "yyyyMMdd").between(DateTime.now(), DateUnit.DAY);
        String delta_msg = "老婆这是我们在一起的第" + between + "天。\n每天为你定时播报\n你亲爱的老公！";
        //获取万年历
        String rtCalendar = this.rtCalendar.getRtCalendar(DateTime.now().toString("yyyyMMdd"));
        //获取天气
        String weather = this.weather.getTodayWeather("罗江");
        //获取一句话
        String oneword = this.oneword.getOneword();

        String text = rtCalendar.concat("\n")
                .concat(weather).concat("\n")
                .concat(oneword).concat("\n")
                .concat(delta_msg);

        String specialWxId = WeChatBotConfig.getSpecialWxId();
        if (StrUtil.isNotEmpty(specialWxId)) {
            handler.sendTextMsg(specialWxId, text);
        }

    }

    public static void main(String[] args) {
        long between = DateTime.of(Constants.START_DATE, "yyyyMMdd").between(DateTime.now(), DateUnit.DAY);
        System.out.println(between);
    }

}
