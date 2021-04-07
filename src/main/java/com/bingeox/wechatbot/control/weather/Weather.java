package com.bingeox.wechatbot.control.weather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.WeatherResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description https://github.com/MZCretin/RollToolsApi#获取特定城市今日天气
 * 获取特定城市今日天气
 * @since 2021/4/8
 **/
@Slf4j
@Component
public class Weather {
    private static final String URL = "https://www.mxnzp.com/api/weather/current/";
    private static final String suffix = "?app_id=qcqfdpg8v0ako9pq&app_secret=WGFKK3FjbDdDMXc5T2l3Q2RFSmpxQT09";

    /**
     * @param cityName
     * @return
     */
    public String getTodayWeather(String cityName){
        log.info("获取 {} 的天气...", cityName);
        String returnText = "";
        try {
            JSONObject resp = HttpClientUtils.httpGet(URL + cityName + suffix);
            if (resp.getIntValue("code") == Constants.ONE){
                WeatherResult data = JSON.parseObject(resp.get("data").toString(), new TypeReference<WeatherResult>() {
                });
                String reportTime = data.getReportTime().trim().split(" ")[0];
                returnText = String.format("今日(%s)%s天气\n气温:%s 天气:%s 湿度:%s\n风力:%s 风向：%s风\n愿你拥有比阳光更明媚的心情",
                        reportTime, cityName, data.getTemp(), data.getWeather(),
                        data.getHumidity(), data.getWindPower(), data.getWindDirection());
            }
        }catch (Exception ex){
            log.error("getTodayWeather error,param:{} ex:{}",cityName, ex.getMessage(), ex);
        }
        return returnText;
    }

    public static void main(String[] args) {
        Weather weather = new Weather();
        System.out.println(weather.getTodayWeather("罗江"));
    }
}
