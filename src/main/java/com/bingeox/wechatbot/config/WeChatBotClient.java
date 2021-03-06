package com.bingeox.wechatbot.config;

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

    public void sendMsg(String json) {
        try {
            this.send(json);
        } catch (Exception ex) {
            log.error("sendMsg failed，send json:{} ex:{}", json, ex);
        }
    }

    @Override
    public void onMessage(String message) {
        log.info("原始消息：{}",message);
//        WeChatMessageHandler handler = applicationContext.getBean(WeChatMessageHandler.class);
//        handler.handMessage(JSON.parseObject(message, BaseMessage.class));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        short httpStatus = serverHandshake.getHttpStatus();
        String httpStatusMessage = serverHandshake.getHttpStatusMessage();
        log.info("正在打开链接....httpStatu:{} httpStatusMessage:{}", httpStatus, httpStatusMessage);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("远程主机{}关闭链接，关闭原因:{}", remote, reason);

    }

    @Override
    public void onError(Exception e) {
        log.info("异常, reason:{} ex:{}", e.getMessage(), e);
    }

}
