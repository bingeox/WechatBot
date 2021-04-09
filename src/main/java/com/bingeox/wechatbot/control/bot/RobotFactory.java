package com.bingeox.wechatbot.control.bot;

import com.bingeox.wechatbot.entity.BotRetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/9
 **/
@Component
public class RobotFactory {

    @Autowired
    private AiQQRobot aiQQRobot;
    @Autowired
    private OwnThinkRobot ownThinkRobot;
    @Autowired
    private RuyiRobot ruyiRobot;
    @Autowired
    private TianRobot tianRobot;
    @Autowired
    private TuLingRobot tuLingRobot;

    public String getMessage(String question){
        //默认使用 ruyiRobot
        String answer = "搜噶";
        BotRetModel ruyi = ruyiRobot.getMessage(question);
        if (ruyi.isRet()){
            answer = ruyi.getMessage();
        } else {
            BotRetModel own = ownThinkRobot.getMessage(question);
            if (own.isRet()){
                answer = own.getMessage();
            } else {
                BotRetModel qq = aiQQRobot.getMessage(question);
                if (qq.isRet()){
                    answer = qq.getMessage();
                } else {
                    BotRetModel tian = tianRobot.getMessage(question);
                    if (tian.isRet()){
                        answer = tian.getMessage();
                    } else {
                        BotRetModel tu = tuLingRobot.getMessage(question);
                        if (tu.isRet()){
                            answer = tu.getMessage();
                        }
                    }
                }
            }
        }
        return answer;
    }
}
