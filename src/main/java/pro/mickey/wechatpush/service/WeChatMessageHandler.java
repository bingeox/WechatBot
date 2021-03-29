package pro.mickey.wechatpush.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.mickey.wechatpush.dto.WeChatMsgDTO;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/26
 **/
@Slf4j
@Component
public class WeChatMessageHandler {

    private WeChatPushService weChatPushService;

    public void hand(String message, WeChatPushService weChatPushService) {
        this.weChatPushService = weChatPushService;
    }

    /**
     * 发送文本消息
     *
     * @param wxid
     * @param text
     */
    public void sendMsg(String wxid, String text) {
        String id = String.valueOf(System.currentTimeMillis());
        String json = WeChatMsgDTO.builder()
                .content(text)
                .wxid(wxid)
                .type(555)
                .id(id)
                .build()
                .toJson();
        log.info("sendMsg:" + json);
        sendMsg(json);
    }

    /**
     * 获取微信所有群
     */
    public void getChatRoomContact() {
        String id = String.valueOf(System.currentTimeMillis());
        String json = WeChatMsgDTO.builder()
                .content("op:list member")
                .wxid("null")
                .type(5010)
                .id(id)
                .build()
                .toJson();
        log.info("getChatRoomContact:" + json);
        sendMsg(json);
    }

    /**
     * 获取所有联系人：包括公众号、群聊、好友
     */
    public void getContact() {
        String id = String.valueOf(System.currentTimeMillis());
        String json = WeChatMsgDTO.builder()
                .content("user list")
                .wxid("null")
                .type(5000)
                .id(id)
                .build()
                .toJson();
        log.info("getContact:" + json);
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
