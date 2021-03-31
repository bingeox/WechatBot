package com.bingeox.wechatbot.control.bot;

import cn.hutool.core.codec.Rot;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.AnswerTypeEnum;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.Result;
import com.bingeox.wechatbot.entity.bot.OwnThinkParam;
import com.bingeox.wechatbot.entity.bot.OwnThinkResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author bingeox
 * @description 思知机器人，接口地址:<https://www.ownthink.com/> userid 可为空
 * @since 2021/3/30
 **/
@Component
public class OwnThinkRobot implements Robot {

    private static final String APP_KEY = "3f9ae1c73db460f4bbb2b491c9119eec";
    private static final String URL = "https://api.ownthink.com/bot";

    /**
     * https://api.ownthink.com/bot?appid=3f9ae1c73db460f4bbb2b491c9119eec&userid=kCjfFafA&spoken=你有多高
     *
     * @param text
     * @return answer
     */
    @Override
    public String getMessage(String text) {
        OwnThinkParam param = new OwnThinkParam(text, APP_KEY, Rot.encode13(USER_ID));
        JSONObject resp = HttpClientUtils.httpPost(URL, (JSONObject) JSON.toJSON(param));
        Result<OwnThinkResult> result = resp.toJavaObject(new TypeReference<Result<OwnThinkResult>>() {
        });
        String answer = "搜噶";
        if (result.getMessage().equals(Constants.SUCCESS)) {
            OwnThinkResult data = result.getData();
            if (data.getType() == AnswerTypeEnum.TXT.getType()) {
                answer = (data.getInfo() != null ? data.getInfo().getText() : answer);
            }
        }
        return answer;
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


