package com.bingeox.wechatbot.entity.message;

import lombok.Data;

@Data
public class NormalMessage extends BaseMessage {
    private String wxid;
    private String status;
}
