package com.bingeox.wechatbot.control.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.constant.ReqTypeEnum;
import com.bingeox.wechatbot.entity.bot.TuLingParam;
import com.bingeox.wechatbot.entity.bot.TuLingResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bingeox
 * @description 图灵机器人  官网：http://www.tuling123.com/
 * 接口地址：https://www.kancloud.cn/turing/www-tuling123-com/718227
 * @since 2021/3/31 0031
 **/
@Component
public class TuLingRobot implements Robot {

    private static final String APP_KEY = "3b4b4d8f-72c6-40a9-b5f8-c058f668c056";
    private static final String URL = "http://openapi.tuling123.com/openapi/api/v2";
    private static final List<Integer> ERROR_CODE = Lists.newArrayList(
            0,6000,5000,4000,4001,4002,4003,4005,4007,4100,4200,4300,4400,4500,4600,4602,7002,8008
    );

    @Override
    public String getMessage(String text) {
        TuLingParam param = new TuLingParam(text, APP_KEY, USER_ID, ReqTypeEnum.TEXT);
        JSONObject resp = HttpClientUtils.httpPost(URL, (JSONObject) JSON.toJSON(param));
        TuLingResult result = resp.toJavaObject(new TypeReference<TuLingResult>() {
        });
        String answer = "搜噶";
        if (!ERROR_CODE.contains(result.getIntent().getCode())){
            TuLingResult.Results results = result.getResults().stream().filter(r -> r.getResultType().equals(Constants.TEXT)).findAny().get();
            answer = (results.getValues() != null ? results.getValues().getValue() : answer);
        }
        return answer;
    }
}