package com.bingeox.wechatbot.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.enums.ReadyState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WeChatBotConfig {

    @Value("${config.weChat.url}")
    private String weChatUrl;

    private static ThreadLocal<String> wxIdLocal = new ThreadLocal<>();

    @Bean
    public WeChatBotClient initWeChatBotClient() throws Exception {
        WeChatBotClient client = new WeChatBotClient(weChatUrl);
        client.setConnectionLostTimeout(0);
        log.info("client connecting...");
        client.connect();

        new Thread(() -> {
            log.info("client state:{}", client.getReadyState());
            while (!client.getReadyState().equals(ReadyState.OPEN)) {
                try {
                    Thread.sleep(200);
                    client.send("connect");
                } catch (Exception e) {
                    log.info("client reconnecting...");
                    client.reconnect();
                }
            }
        }).start();

        return client;
    }

    public static String getSpecialWxId() {
        return wxIdLocal.get();
    }

    public static void setSpecialWxId(String specialWxId) {
        wxIdLocal.set(specialWxId);
    }

}
