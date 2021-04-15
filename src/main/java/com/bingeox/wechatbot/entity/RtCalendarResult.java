package com.bingeox.wechatbot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/7
 **/
@Data
public class RtCalendarResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;
    /**
     * {
     * "code": 1,
     * "msg": "数据返回成功",
     * "data": {
     * "date": "2018-11-21",
     * "weekDay": 3,
     * "yearTips": "戊戌",
     * "type": 0,
     * "typeDes": "工作日",
     * "chineseZodiac": "狗",
     * "solarTerms": "立冬后",
     * "avoid": "嫁娶.安葬",
     * "lunarCalendar": "十月十四",
     * "suit": "破屋.坏垣.祭祀.余事勿取",
     * "dayOfYear": 325,
     * "weekOfYear": 47,
     * "constellation": "天蝎座",
     * "indexWorkDayOfMonth": 1
     * }
     * }
     */
    private String date;
    private Integer weekDay;
    private String yearTips;
    private Integer type;
    private String typeDes;
    private String chineseZodiac;
    private String solarTerms;
    private String avoid;
    private String lunarCalendar;
    private String suit;
    private Integer dayOfYear;
    private Integer weekOfYear;
    private String constellation;
    private Integer indexWorkDayOfMonth;
}
