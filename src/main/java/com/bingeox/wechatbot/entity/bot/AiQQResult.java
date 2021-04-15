package com.bingeox.wechatbot.entity.bot;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/31
 **/
@Data
public class AiQQResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    /**
     * {
     * "ret": 0,
     * "msg": "ok",
     * "data": {
     * "session": "10000",
     * "answer": "我叫小豪豪~"
     * }
     * }
     */
    private String session;
    private String answer;

}
