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
public class RetModel {

    private boolean ret;
    private String message;

    private RetModel(boolean ret, String message) {
        this.ret = ret;
        this.message = message;
    }

    public static RetModel success(String message) {
        return new RetModel(true, message);
    }

    public static RetModel fail() {
        return new RetModel(false, null);
    }
}
