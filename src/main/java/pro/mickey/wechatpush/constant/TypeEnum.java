package pro.mickey.wechatpush.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description 类型枚举
 * @since 2021/3/29 0029
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TypeEnum {

    NORMAL_MSG(1,"普通消息"),
    CALLBACK(555, "发送消息回调"),
    HEARTBEAT(5005, "心跳"),
    CONTRACT(5001, "好友信息"),
    CHAT_ROOM(5010, "群信息");

    private int type;
    private String desc;

}
