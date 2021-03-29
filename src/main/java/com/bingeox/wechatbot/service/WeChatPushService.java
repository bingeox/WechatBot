package com.bingeox.wechatbot.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.bingeox.wechatbot.entity.BaseMessage;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class WeChatPushService extends WebSocketClient {

    public WeChatPushService(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onMessage(String message) {
        WeChatMessageHandler.getInstance().handMessage(JSON.parseObject(message, BaseMessage.class));
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
