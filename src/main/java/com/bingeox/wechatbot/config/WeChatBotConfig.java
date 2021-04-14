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

    @Bean
    public WeChatBotClient initWeChatBotClient() throws Exception {
        WeChatBotClient client = new WeChatBotClient(weChatUrl);
        client.setConnectionLostTimeout(0);
        client.connect();
        while (!client.getReadyState().equals(ReadyState.OPEN)) {
            Thread.sleep(200);
            log.info("正在链接...");
        }
        return client;
    }
}
