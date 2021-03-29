package pro.mickey.wechatpush.entity;

import lombok.Data;

@Data
public class RoomMessage extends BaseMessage {
    private String roomid;
    private String nickname;
}
