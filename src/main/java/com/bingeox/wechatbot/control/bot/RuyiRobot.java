package com.bingeox.wechatbot.control.bot;

import cn.hutool.core.codec.Rot;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.Result;
import com.bingeox.wechatbot.entity.bot.RuyiParam;
import com.bingeox.wechatbot.entity.bot.RuyiResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * @author bingeox
 * @description 海知智能机器人 <https://ruyi.ai/> 功能很强大，不仅仅用于聊天。需申请 key，免费
 * 文档说明：<http://docs.ruyi.ai/502931>
 * @since 2021/3/30
 **/
@Component
public class RuyiRobot implements Robot {

    private static final String APP_KEY = "3b4b4d8f-72c6-40a9-b5f8-c058f668c056";
    private static final String URL = "http://api.ruyi.ai/v1/message";

    @Override
    public String getMessage(String text) {
        RuyiParam param = new RuyiParam(text, APP_KEY, Rot.encode13(USER_ID));
        JSONObject resp = HttpClientUtils.httpPost(URL, (JSONObject) JSON.toJSON(param));
        Result<RuyiResult> result = resp.toJavaObject(new TypeReference<Result<RuyiResult>>() {
        });
        String answer = "搜噶";
        if (result.getCode() == Constants.ZERO || result.getCode() == Constants.TWO_HUNDRED){
            List<RuyiResult.Intent.Output> outputs = result.getData().getIntents().get(0).getOutputs();
            RuyiResult.Intent.Output output = outputs.stream().filter(o -> o.getType().equals(Constants.WECHAT_TEXT)).findFirst().get();
            answer = (output.getProperty() != null ? output.getProperty().getText() : answer);
        }
        return answer;
    }

    public static void main(String[] args) {
        RuyiRobot robot = new RuyiRobot();
        for (int i = 0; i < 3; i++) {
            Scanner input = new Scanner(System.in);
            String str = input.next();
            String message = robot.getMessage(str);
            System.out.println(message);
        }
    }
}