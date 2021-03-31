package com.bingeox.wechatbot.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "code")
    @JsonAlias(value = {"ret"})
    private int code;

    @JsonProperty(value = "message")
    @JsonAlias(value = {"msg"})
    private String message;

    @JsonProperty(value = "data")
    @JsonAlias(value = {"result", "newslist"})
    private T data;

}
