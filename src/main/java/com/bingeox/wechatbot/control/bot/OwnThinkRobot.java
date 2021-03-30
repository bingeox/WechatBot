package com.bingeox.wechatbot.control.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bingeox.wechatbot.entity.OwnThinkReq;
import com.bingeox.wechatbot.entity.OwnThinkResp;
import com.bingeox.wechatbot.utils.HttpClientUtils;

import java.util.Scanner;

/**
 * @author xiaobing@meicai.cn
 * @description 思知机器人，接口地址:<https://www.ownthink.com/> userid 可为空
 * @since 2021/3/30
 **/
public class OwnThinkRobot {

    private static final String APPID = "3f9ae1c73db460f4bbb2b491c9119eec";
    private static final String USERID = "kCjfFafA";
    private static final String URL = "https://api.ownthink.com/bot";

    /**
     * https://api.ownthink.com/bot?appid=3f9ae1c73db460f4bbb2b491c9119eec&userid=kCjfFafA&spoken=你有多高
     *
     * @param text
     * @return
     */
    public String getMessage(String text) {
        OwnThinkReq req = new OwnThinkReq(text, APPID, USERID);
        JSONObject resp = HttpClientUtils.httpPost(URL, JSON.toJSONString(req));
        if (resp.get("message").equals("success")) {
            OwnThinkResp data = JSONObject.parseObject(resp.get("data").toString(), OwnThinkResp.class);
            if (data.getType() == 5000) {
                return data.getInfo().getText();
            }
            return "";
        }
        return "搜噶";
    }

    public static void main(String[] args) {
        OwnThinkRobot robot = new OwnThinkRobot();
        for (int i = 0; i < 3; i++) {
            Scanner input = new Scanner(System.in);
            String str = input.next();
            String message = robot.getMessage(str);
            System.out.println(message);
        }

    }

}


