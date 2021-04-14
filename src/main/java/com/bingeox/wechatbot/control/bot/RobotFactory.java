package com.bingeox.wechatbot.control.bot;

import com.bingeox.wechatbot.entity.RetModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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

    private static List<Robot> robots;

    @PostConstruct
    public void initRobot(){
        robots = Lists.newLinkedList();
        robots.add(ruyiRobot);
        robots.add(ownThinkRobot);
        robots.add(tianRobot);
        robots.add(aiQQRobot);
//        robots.add(tuLingRobot);
    }

    public String getMessage(String question){
        //默认使用 ruyiRobot
        String answer = "搜噶";
        for (Robot robot : robots) {
            RetModel model = robot.getMessage(question);
            if (model.isRet()){
                answer = model.getMessage();
                break;
            }
        }
        return answer;
    }
}
