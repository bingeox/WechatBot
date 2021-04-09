package com.bingeox.wechatbot.control.bot;

import cn.hutool.core.codec.Base32;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.BotRetModel;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30 0030
 **/
public interface Robot {

    String USER_ID = Base32.encode(Constants.USER_ID);

    /**
     * 机器人自动回复
     *
     * @param text
     * @return answer
     */
    BotRetModel getMessage(String text);
}
