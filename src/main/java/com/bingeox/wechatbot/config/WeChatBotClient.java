package com.bingeox.wechatbot.config;

import com.alibaba.fastjson.JSON;
import com.bingeox.wechatbot.entity.message.BaseMessage;
import com.bingeox.wechatbot.handler.WeChatMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class WeChatBotClient extends WebSocketClient {

    @Autowired
    private ApplicationContext applicationContext;

    public WeChatBotClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onMessage(String message) {
        WeChatMessageHandler handler = applicationContext.getBean(WeChatMessageHandler.class);
        handler.handMessage(JSON.parseObject(message, BaseMessage.class));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        short httpStatus = serverHandshake.getHttpStatus();
        String httpStatusMessage = serverHandshake.getHttpStatusMessage();
        log.info("Connection opened....httpStatu:{} httpStatusMessage:{}", httpStatus, httpStatusMessage);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Connection closed by {}  Code: {} Reason: ",( remote ? "remote peer" : "us" ), code, reason);

    }

    @Override
    public void onError(Exception e) {
        log.info("Connection error, reason:{} ex:{}", e.getMessage(), e);
    }

}
