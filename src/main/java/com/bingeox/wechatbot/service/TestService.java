package com.bingeox.wechatbot.service;

import com.bingeox.wechatbot.handler.WeChatMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    @Autowired
    private WeChatMessageHandler handler;

    @Value("${special.wxid}")
    private String specialWxId;

    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void showNews() {
        log.info("------sendTextMsg-------");
        handler.sendTextMsg("filehelper", "我用Java发送的微信！");
//        log.info("------sendPicMsg-------");
//        handler.sendPicMsg("filehelper", "C:\\Users\\Administrator\\Desktop\\pic\\test.png");
//        log.info("------getChatRoomContactList-------");
//        handler.getChatRoomContactList();
//        log.info("------getContactList-------");
//        handler.getContactList();
//        log.info("------getChatNickByRoomId-------");
//        handler.getChatNickByRoomId("13986924266@chatroom");
//        log.info("------getPersonalDetail-------");
//        handler.getPersonalDetail(specialWxId);
//        log.info("------getPersonalInfo-------");
//        handler.getPersonalInfo();
    }
}
