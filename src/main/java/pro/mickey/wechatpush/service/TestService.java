package pro.mickey.wechatpush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private WeChatPushService weChatService;

    @Value("${special.wxid}")
    private String specialWxId;

    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void showNews() {
//        weChatService.sendMsg(specialWxId, "我用Java发送的微信！");
//        weChatService.getChatRoomContact();
    }
}
