package pro.mickey.wechatpush.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.mickey.wechatpush.constant.Constants;
import pro.mickey.wechatpush.constant.TypeEnum;
import pro.mickey.wechatpush.entity.BaseMessage;

/**
 * doc: https://www.showdoc.com.cn/wechatbot?page_id=3905634824500582
 */
@Slf4j
@Component
public class WeChatMessageHandler {

    @Autowired
    private WeChatPushService weChatPushService;

    @Value("${special.wxid}")
    private String specialWxId;

    private static final WeChatMessageHandler INSTANCE = new WeChatMessageHandler();

    public static WeChatMessageHandler getInstance(){
        return INSTANCE;
    }

    public void handMessage(BaseMessage message) {
        if (message.getType() == TypeEnum.NORMAL_MSG.getType()){
            log.info("收到消息：" + message);
            if (message.getReceiver().equals(Constants.FILE_HELPER)){
                sendTextMsg(Constants.FILE_HELPER, message.getContent());
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
        String id = String.valueOf(System.currentTimeMillis());
        BaseMessage sendMsg = new BaseMessage();
        sendMsg.setId(id);
        sendMsg.setWxid(wxid);
        sendMsg.setContent(text);
        sendMsg.setType(TypeEnum.CALLBACK.getType());

        String json = sendMsg.toJson();
        log.info("sendTextMsg:" + json);
        sendMsg(json);
    }

    /**
     * 获取微信所有群
     */
    public void getChatRoomContact() {
        String id = String.valueOf(System.currentTimeMillis());
        BaseMessage sendMsg = new BaseMessage();
        sendMsg.setId(id);
        sendMsg.setWxid(Constants.NULL_WXID);
        sendMsg.setContent(Constants.CHATROOM_LIST);
        sendMsg.setType(TypeEnum.CHAT_ROOM.getType());

        String json = sendMsg.toJson();
        log.info("getChatRoomContactList:" + json);
        sendMsg(json);
    }

    /**
     * 获取所有联系人：包括公众号、群聊、好友
     */
    public void getContact() {
        String id = String.valueOf(System.currentTimeMillis());
        BaseMessage sendMsg = new BaseMessage();
        sendMsg.setId(id);
        sendMsg.setWxid(Constants.NULL_WXID);
        sendMsg.setContent(Constants.CONTRACT_LIST);
        sendMsg.setType(5000);

        String json = sendMsg.toJson();
        log.info("getContactList:" + json);
        sendMsg(json);
    }

    private void sendMsg(String json) {
        try {
            weChatPushService.send(json);
        } catch (Exception e) {
            /**
             * 这块我本来用于发送微信失败补偿邮件
             */
        }
    }


}
