package com.bingeox.wechatbot.control.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.constant.ReqTypeEnum;
import com.bingeox.wechatbot.entity.RetModel;
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
    /**
     * 5000	无解析结果
     * 6000	暂不支持该功能
     * 4000	请求参数格式错误
     * 4001	加密方式错误
     * 4002	无功能权限
     * 4003	该apikey没有可用请求次数
     * 4005	无功能权限
     * 4007	apikey不合法
     * 4100	userid获取失败
     * 4200	上传格式错误
     * 4300	批量操作超过限制
     * 4400	没有上传合法userid
     * 4500	userid申请个数超过限制
     * 4600	输入内容为空
     * 4602	输入文本内容超长(上限150)
     * 7002	上传信息失败
     * 8008	服务器错误
     * 0	上传成功
     */
    private static final List<Integer> ERROR_CODE = Lists.newArrayList(
            0, 6000, 5000, 4000, 4001, 4002, 4003, 4005, 4007, 4100, 4200, 4300, 4400, 4500, 4600, 4602, 7002, 8008
    );

    @Override
    public RetModel getMessage(String text) {
        TuLingParam param = new TuLingParam(text, APP_KEY, USER_ID, ReqTypeEnum.TEXT);
        JSONObject resp = HttpClientUtils.httpPost(URL, (JSONObject) JSON.toJSON(param));
        TuLingResult result = resp.toJavaObject(new TypeReference<TuLingResult>() {
        });
        String answer = "搜噶";
        if (!ERROR_CODE.contains(result.getIntent().getCode())) {
            TuLingResult.Results results = result.getResults().stream().filter(r -> r.getResultType().equals(Constants.TEXT)).findAny().get();
            answer = (results.getValues() != null ? results.getValues().getValue() : answer);
            return RetModel.success(answer);
        }
        return RetModel.fail();
    }
}