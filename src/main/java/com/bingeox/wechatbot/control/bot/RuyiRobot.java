package com.bingeox.wechatbot.control.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.RetModel;
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

    /**
     * Code
     * 0／200	成功	请求成功
     * 400	无效请求	某些必需参数缺失或参数值错误，详见msg字段
     * 401	未授权	授权失败，app_key缺失或错误
     * 403	请求被禁止	有效请求，但服务拒绝响应，请联系contact@ruyi.ai
     * 408	请求超时	请求响应超时，一般响应时间设置为2000ms以内
     * 429	短时间内大量访问	短时间内请求数过多
     * 500	内部错误	服务处理异常
     * 503	服务不可用	服务异常或正在维护
     */
    @Override
    public RetModel getMessage(String text) {
        RuyiParam param = new RuyiParam(text, APP_KEY, USER_ID);
        JSONObject resp = HttpClientUtils.httpPost(URL, (JSONObject) JSON.toJSON(param));
        String answer = "搜噶";
        if (resp.getIntValue("code") == Constants.ZERO || resp.getIntValue("code") == Constants.TWO_HUNDRED){
            RuyiResult ruyiResult = JSON.parseObject(resp.get("result").toString(), new TypeReference<RuyiResult>() {
            });
            List<RuyiResult.Intent.Output> outputs = ruyiResult.getIntents().get(0).getOutputs();
            RuyiResult.Intent.Output output = outputs.stream().filter(o -> o.getType().equals(Constants.WECHAT_TEXT)).findFirst().get();
            answer = (output.getProperty() != null ? output.getProperty().getText() : answer);
            return RetModel.success(answer);
        }
        return RetModel.fail();
    }

    public static void main(String[] args) {
        RuyiRobot robot = new RuyiRobot();
        for (int i = 0; i < 3; i++) {
            Scanner input = new Scanner(System.in);
            String str = input.next();
            RetModel model = robot.getMessage(str);
            System.out.println(model.getMessage());
        }
    }
}