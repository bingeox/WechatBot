package com.bingeox.wechatbot.entity;

import lombok.Data;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/30
 **/
@Data
public class BotResult<T> {

    private int code;
    private String message;
    private String msg;
    private T data;
    private T result;

}
