package com.bingeox.wechatbot.control.calendar;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.constant.WeekEnum;
import com.bingeox.wechatbot.entity.RtCalendarResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaobing@meicai.cn
 * @description 获取指定日期的节假日及万年历信息
 * https://github.com/MZCretin/RollToolsApi
 * {"code":1,"msg":"数据返回成功","data":{
 * "date":"2019-06-27","weekDay":4,"yearTips":"己亥",
 * "type":0,"typeDes":"工作日","chineseZodiac":"猪","solarTerms":"夏至后",
 * "avoid":"移徙.入宅.安葬","lunarCalendar":"五月廿五",
 * "suit":"订盟.纳采.出行.祈福.斋醮.安床.会亲友",
 * "dayOfYear":178,"weekOfYear":26,"constellation":"巨蟹座"}}
 * @since 2021/4/7
 **/
@Slf4j
@Component
public class RtCalendar {

    private static final List<String> STFT = Arrays.asList(
            "冬至", "小寒", "大寒", "立春", "雨水", "惊蛰",
            "春分", "清明", "谷雨", "立夏", "小满", "芒种",
            "夏至", "小暑", "大暑", "立秋", "处暑", "白露",
            "秋分", "寒露", "霜降", "立冬", "小雪", "大雪");

    private static final String URL = "https://www.mxnzp.com/api/holiday/single/";
    private static final String suffix = "?app_id=qcqfdpg8v0ako9pq&app_secret=WGFKK3FjbDdDMXc5T2l3Q2RFSmpxQT09";

    /**
     * @param date 日期 格式 yyyyMMdd
     * @return
     */
    public String getRtCalendar(String date) {
        log.info("获取 {} 的日历...", date);
        String returnText = "";
        try {
            JSONObject resp = HttpClientUtils.httpGet(URL + date + suffix);
            if (resp.getIntValue("code") == Constants.ONE) {
                RtCalendarResult data = JSON.parseObject(resp.get("data").toString(), new TypeReference<RtCalendarResult>() {
                });
                String solarTerms = data.getSolarTerms();
                if (!STFT.contains(solarTerms)) {
                    solarTerms = "";
                }
                String suit = data.getSuit();
                if (StringUtils.isEmpty(suit)) {
                    suit = "无";
                }
                String avoid = data.getAvoid();
                if (StringUtils.isEmpty(avoid)) {
                    avoid = "无";
                }

                returnText = String.format("%s %s 农历%s %s\n【宜】%s\n【忌】%s",
                        data.getDate(), WeekEnum.getDayByCode(data.getWeekDay()), data.getLunarCalendar(), solarTerms, suit, avoid);

            }
        } catch (Exception ex) {
            log.error("getRtCalendar error,param:{} ex:{}", date, ex.getMessage(), ex);
        }
        return returnText;
    }

    public static void main(String[] args) {
        RtCalendar rtCalendar = new RtCalendar();
        System.out.println(rtCalendar.getRtCalendar(DateTime.now().toString("yyyyMMdd")));

    }

}

