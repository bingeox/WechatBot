package com.bingeox.wechatbot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/9
 **/
@Data
@NoArgsConstructor
public class BotRetModel {

    private boolean ret;
    private String message;

    private BotRetModel(boolean ret, String message) {
        this.ret = ret;
        this.message = message;
    }

    public static BotRetModel success(String message){
        return new BotRetModel(true, message);
    }

    public static BotRetModel fail(){
        return new BotRetModel(false, null);
    }
}
