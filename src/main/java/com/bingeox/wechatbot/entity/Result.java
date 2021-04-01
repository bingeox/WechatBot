package com.bingeox.wechatbot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30
 **/
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    @JSONField(name = "ret")
    private int code;

    @JSONField(name = "msg")
    private String message;

    @JSONField(name = "result")
    private T data;

    @JSONField(name = "newslist")
    private T newsList;

}
