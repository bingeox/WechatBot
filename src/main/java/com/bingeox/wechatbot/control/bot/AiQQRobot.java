package com.bingeox.wechatbot.control.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.Result;
import com.bingeox.wechatbot.entity.bot.AiQQParam;
import com.bingeox.wechatbot.entity.bot.AiQQResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author bingeox
 * @description 智能闲聊（腾讯）<https://ai.qq.com/product/nlpchat.shtml>
 *     接口文档：<https://ai.qq.com/doc/nlpchat.shtml>
 * @since 2021/4/1
 **/
@Component
public class AiQQRobot implements Robot {

    private static final Long APP_ID = 2169150286L;
    private static final String APP_KEY = "AbdjjKfTouCXKExZ";
    private static final String URL = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";

    /**
     * ret 0表示成功，非0表示出错
     * msg	是	string	返回信息；ret非0时表示出错时错误原因
     * data	是	object	返回数据；ret为0时有意义
     */
    @Override
    public String getMessage(String text) {
        AiQQParam param = new AiQQParam(text, APP_ID, USER_ID);
        param.setReqSign(APP_KEY);
        JSONObject resp = HttpClientUtils.httpPost(URL, (JSONObject) JSON.toJSON(param));
        Result<AiQQResult> result = resp.toJavaObject(new TypeReference<Result<AiQQResult>>() {
        });
        String answer = "搜噶";
        if (result.getCode() == Constants.ZERO){
            AiQQResult data = result.getData();
            answer = (data != null ? data.getAnswer() : answer);
        }
        return answer;
    }

    public static void main(String[] args) {
        AiQQRobot robot = new AiQQRobot();
        for (int i = 0; i < 3; i++) {
            Scanner input = new Scanner(System.in);
            String str = input.next();
            String message = robot.getMessage(str);
            System.out.println(message);
        }

    }
}