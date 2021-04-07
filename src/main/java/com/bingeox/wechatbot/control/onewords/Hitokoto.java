package com.bingeox.wechatbot.control.onewords;

import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description 从『一言』获取信息。(官网：https://hitokoto.cn/)
 * @since 2021/4/8
 **/
@Slf4j
@Component
public class Hitokoto {
    private static final String URL = "https://v1.hitokoto.cn/?encode=text";

    public String getHitokotoInfo(){
        String hitokoto = "";
        try {
            hitokoto = HttpClientUtils.httpGet_Str(URL);
        }catch (Exception ex){
            log.error("getHitokotoInfo error, ex:{}", ex.getMessage(), ex);
        }
        return hitokoto;
    }

    public static void main(String[] args) {
        Hitokoto hitokoto = new Hitokoto();
        System.out.println(hitokoto.getHitokotoInfo());
    }
}
