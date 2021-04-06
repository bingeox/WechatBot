package com.bingeox.wechatbot.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
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

    @JsonAlias("ret")
    private int code;

    @JsonAlias("msg")
    private String message;

    @JsonAlias({"result", "newslist"})
    private T data;

}
