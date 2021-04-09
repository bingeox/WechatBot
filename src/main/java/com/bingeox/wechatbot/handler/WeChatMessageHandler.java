package com.bingeox.wechatbot.handler;

import com.bingeox.wechatbot.config.WeChatBotClient;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.constant.TypeEnum;
import com.bingeox.wechatbot.control.bot.RobotFactory;
import com.bingeox.wechatbot.entity.message.BaseMessage;
import com.bingeox.wechatbot.entity.message.NormalMessage;
import com.bingeox.wechatbot.entity.message.RoomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * doc: https://www.showdoc.com.cn/wechatbot?page_id=3905634824500582
 */
@Slf4j
@Component
public class WeChatMessageHandler {

    @Autowired
    private WeChatBotClient client;
    @Autowired
    private RobotFactory robotFactory;

    @Value("${special.wxid}")
    private String specialWxId;

    /**
     * 控制是否需要机器人回复
     */
    private static boolean isPause = true;

    public void handMessage(BaseMessage message) {
        log.info("收到消息：" + message);
        if (message.getType() == TypeEnum.RECV_TXT_MSG.getType()) {
            if (message.getSender().equals(specialWxId) && isPause) {
                sendTextMsg(specialWxId, robotFactory.getMessage(message.getContent().toString()));
            }

            if (message.getSender().equals(Constants.SELF)) {
                if (Constants.PAUSE.endsWith(message.getContent().toString())){
                    isPause = false;
                }

            }
        }
    }

    /**
     * 发送文本消息
     *
     * @param wxid
     * @param text
     */
    public void sendTextMsg(String wxid, Object text) {
        NormalMessage sendMsg = new NormalMessage();
        sendMsg.setWxid(wxid);
        sendMsg.setContent(text);
        sendMsg.setType(TypeEnum.TXT_MSG.getType());

        String json = sendMsg.toJson();
        log.info("sendTextMsg:" + json);
        sendMsg(json);
    }

    /**
     * 发送图片
     *
     * @param wxid
     * @param picPath
     */
    public void sendPicMsg(String wxid, String picPath) {
        NormalMessage sendMsg = new NormalMessage();
        sendMsg.setWxid(wxid);
        sendMsg.setContent(picPath);
        sendMsg.setType(TypeEnum.PIC_MSG.getType());

        String json = sendMsg.toJson();
        log.info("sendPicMsg:" + json);
        sendMsg(json);
    }

    /**
     * 发送群 @ 消息
     *
     * @param roomId
     * @param wxid
     * @param content
     */
    public void sendAtMsg(String roomId, String wxid, Object content) {
        RoomMessage sendMsg = new RoomMessage();
        sendMsg.setWxid(wxid);
        sendMsg.setContent(content);
        sendMsg.setType(TypeEnum.AT_MSG.getType());
        sendMsg.setRoomid(roomId);
        sendMsg.setNickname("[微笑]Python");

        String json = sendMsg.toJson();
        log.info("sendAtMsg:" + json);
        sendMsg(json);
    }

    /**
     * 获取微信所有群，只有 roomId和群成员wxid
     */
    public void getChatRoomContactList() {
        RoomMessage sendMsg = new RoomMessage();
        sendMsg.setWxid(Constants.NULL);
        sendMsg.setContent(Constants.CHATROOM_LIST);
        sendMsg.setType(TypeEnum.CHATROOM_MEMBER.getType());

        String json = sendMsg.toJson();
        log.info("getChatRoomContactList:" + json);
        sendMsg(json);
    }

    /**
     * 获取所有联系人：包括公众号、群聊、好友 (用户名字和wxid)
     */
    public void getContactList() {
        NormalMessage sendMsg = new NormalMessage();
        sendMsg.setWxid(Constants.NULL);
        sendMsg.setContent(Constants.CONTRACT_LIST);
        sendMsg.setType(TypeEnum.USER_LIST.getType());

        String json = sendMsg.toJson();
        log.info("getContactList:" + json);
        sendMsg(json);
    }

    /**
     * 根据群id获取群成员和昵称
     *
     * @param roomId
     */
    public void getChatNickByRoomId(String roomId) {
        RoomMessage sendMsg = new RoomMessage();
        sendMsg.setWxid(Constants.ROOT);
        sendMsg.setContent(roomId);
        sendMsg.setType(TypeEnum.CHATROOM_MEMBER_NICK.getType());

        String json = sendMsg.toJson();
        log.info("getChatNickByRoomId:" + json);
        sendMsg(json);
    }

    /**
     * debug调试信息开关，默认为关 on,off
     *
     * @param switchs
     */
    public void debugSwitch(String switchs) {
        NormalMessage sendMsg = new NormalMessage();
        sendMsg.setWxid(Constants.ROOT);
        sendMsg.setContent(switchs);
        sendMsg.setType(TypeEnum.DEBUG_SWITCH.getType());

        String json = sendMsg.toJson();
        log.info("debugSwitch:" + json);
        sendMsg(json);
    }

    /**
     * 获取好友详细信息
     *
     * @param wxid
     */
    public void getPersonalDetail(String wxid) {
        NormalMessage sendMsg = new NormalMessage();
        sendMsg.setWxid(wxid);
        sendMsg.setContent(Constants.PERSONAL_DETAIL);
        sendMsg.setType(TypeEnum.PERSONAL_DETAIL.getType());

        String json = sendMsg.toJson();
        log.info("getPersonalDetail:" + json);
        sendMsg(json);
    }

    /**
     * 获取微信个人信息
     */
    public void getPersonalInfo() {
        NormalMessage sendMsg = new NormalMessage();
        sendMsg.setWxid(Constants.ROOT);
        sendMsg.setContent(Constants.PERSONAL_INFO);
        sendMsg.setType(TypeEnum.PERSONAL_INFO.getType());

        String json = sendMsg.toJson();
        log.info("getPersonalInfo:" + json);
        sendMsg(json);
    }

    private void sendMsg(String json) {
        try {
            client.send(json);
        } catch (Exception e) {
            log.info("send msg failed, send json:{} ex:{}",json, e);
        }
    }

}
