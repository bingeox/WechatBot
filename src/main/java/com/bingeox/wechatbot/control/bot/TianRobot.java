package com.bingeox.wechatbot.control.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.constant.ReqTypeEnum;
import com.bingeox.wechatbot.entity.RetModel;
import com.bingeox.wechatbot.entity.bot.TianParam;
import com.bingeox.wechatbot.entity.bot.TianResult;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * @author xiaobing@meicai.cn
 * @description 天行机器人 申请地址( https://www.tianapi.com/apiview/47 )
 * @since 2021/3/31
 **/
@Component
public class TianRobot implements Robot {

    private static final String APP_KEY = "6d82ba25df8a6fd82d320c47b1d197f8";
    private static final String URL = "http://api.tianapi.com/txapi/robot/index";
    private static final String TU_URL = "http://api.tianapi.com/txapi/tuling/index";

    /**
     * 100	内部服务器错误	出现此错误码请及时反馈或等待修复
     * 110	接口暂时维护中	接口暂时关闭维护中，请注意相关公告
     * 120	IP请求来源受限	设置了IP白名单，来源IP不在白名单内
     * 130	分钟请求频率超限	API超出了分钟调用最大频率被暂时限制
     * 140	API没有调用权限	请检查接口是否被自行停用或被官方禁用
     * 150	接口可用次数不足	接口可用次数不足，请在接口列表查看
     * 160	账号未申请该接口	请先在对应的接口文档页面申请接口，点此查看帮助
     * 170	Referer请求来源受限	设置了Referer白名单，来源Referer不在白名单内
     * 230	key错误或为空	请检查apikey是否填写错误，点此查看帮助
     * 240	缺少key参数	请检查是否传递了key参数，如变量请检查是否赋值
     * 250	数据返回为空	数据查询或转换失败，请确保输入值正确或重试
     * 260	关键词不得为空	请检查word参数是否传递了空值
     * 270	缺少有效数据	接口需要传递数据，请参考接口文档的说明
     * 280	缺少必要的参数	缺少必填的参数，请根据接口文档检查
     * 290	超过最大输入字节限制	超出最大字符，请查看对应的接口文档说明
     * 1开头的是系统级错误，2开头的是用户级错误，其中200代码表示请求成功处理。
     */
    @Override
    public RetModel getMessage(String text) {
        TianParam param = new TianParam(text, APP_KEY, USER_ID, ReqTypeEnum.TEXT.getType());

        String turl = TU_URL + "?key=" + APP_KEY + "&question=" + text;
        String answer = "搜噶";
        JSONObject tu_resp = HttpClientUtils.httpGet(turl);
        if (tu_resp.getIntValue("code") != Constants.TWO_HUNDRED){
            String url = URL + "?key=" + APP_KEY + "&question=" + text;
            JSONObject resp = HttpClientUtils.httpGet(url);
            if (resp.getIntValue("code") == Constants.TWO_HUNDRED){
                List<TianResult> newslist = JSON.parseObject(resp.get("newslist").toString(), new TypeReference<List<TianResult>>() {
                });
                TianResult tianResult = newslist.get(0);
                answer = (tianResult != null ? tianResult.getReply() : answer);
                answer = answer.replaceAll("\\{robotname}","");
                answer = answer.replaceAll("\\{appellation}","");
                return RetModel.success(answer);
            }
            return RetModel.fail();
        }
        List<TianResult> newslist = JSON.parseObject(tu_resp.get("newslist").toString(), new TypeReference<List<TianResult>>() {
        });
        TianResult tianResult = newslist.get(0);
        answer = (tianResult != null ? tianResult.getReply() : answer);
        answer = answer.replaceAll("\\{robotname}","");
        answer = answer.replaceAll("\\{appellation}","");
        return RetModel.success(answer);

    }

    public static void main(String[] args) {
        TianRobot robot = new TianRobot();
        for (int i = 0; i < 3; i++) {
            Scanner input = new Scanner(System.in);
            String str = input.next();
            RetModel model = robot.getMessage(str);
            System.out.println(model.getMessage());
        }
    }

}