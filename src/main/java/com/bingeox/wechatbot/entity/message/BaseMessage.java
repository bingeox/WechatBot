package com.bingeox.wechatbot.entity.message;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseMessage implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;
    private int type;
    private Object content;
    private String id;
    private String receiver;
    private String sender;
    private String srvid;
    private String time;
    private String ext;

    public BaseMessage() {
        this.id = String.valueOf(System.currentTimeMillis());
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

}
