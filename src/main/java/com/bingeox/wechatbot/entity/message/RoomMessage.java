package com.bingeox.wechatbot.entity.message;

import lombok.Data;

@Data
public class RoomMessage extends BaseMessage {
    private String wxid;
    private String status;
    private String roomid;
    private String nickname;
}
