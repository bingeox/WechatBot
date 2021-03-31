package com.bingeox.wechatbot.entity.bot;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30
 **/
@Data
public class OwnThinkResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;
    // 答案类型，5000文本类型
    private int type;
    private Info info;

    @Data
    public static class Info implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;
        // 机器人返回的答案
        private String text;
    }

}
