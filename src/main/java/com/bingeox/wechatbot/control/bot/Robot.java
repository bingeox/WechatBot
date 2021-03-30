package com.bingeox.wechatbot.control.bot;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/30 0030
 **/
public interface Robot {

    String USER_ID = "Wechat-bot-001";

    /**
     * 机器人自动回复
     *
     * @param text
     * @return answer
     */
    String getMessage(String text);
}
