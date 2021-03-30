package com.bingeox.wechatbot.constant;

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

    HEART_BEAT(5005),
    RECV_TXT_MSG(1),
    RECV_PIC_MSG(3),
    USER_LIST(5000),
    GET_USER_LIST_SUCCESS(5001),
    GET_USER_LIST_FAIL(5002),
    TXT_MSG(555),
    PIC_MSG(500),
    AT_MSG(550),
    CHATROOM_MEMBER(5010),
    CHATROOM_MEMBER_NICK(5020),
    PERSONAL_INFO(6500),
    DEBUG_SWITCH(6000),
    PERSONAL_DETAIL(6550),
    DESTROY_ALL(9999),
    //微信好友请求消息
    NEW_FRIEND_REQUEST(37),
    //同意微信好友请求消息
    AGREE_TO_FRIEND_REQUEST(10000);

    private int type;

}
