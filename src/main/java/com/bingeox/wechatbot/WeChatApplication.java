package com.bingeox.wechatbot;

import com.bingeox.wechatbot.service.WeChatBotClient;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.enums.ReadyState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class WeChatApplication {

    @Value("${config.weChat.url}")
    private String weChatUrl;

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
    }

    @Bean
    public WeChatBotClient initWeChatBotClient() throws Exception {
        WeChatBotClient client = new WeChatBotClient(weChatUrl);
        client.connect();
        while (!client.getReadyState().equals(ReadyState.OPEN)) {
            Thread.sleep(200);
            log.info("正在链接...");
        }
        return client;
    }
}
