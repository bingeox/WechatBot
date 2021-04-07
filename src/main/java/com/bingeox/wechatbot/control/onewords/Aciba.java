package com.bingeox.wechatbot.control.onewords;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bingeox.wechatbot.entity.AcibInfo;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description 从词霸中获取每日一句，带英文。
 * @since 2021/4/7
 **/
@Slf4j
@Component
public class Aciba {

    private static final String URL = "http://open.iciba.com/dsapi";

    private AcibInfo getAcibInfo() {
        AcibInfo data = new AcibInfo();
        try {
            JSONObject resp = HttpClientUtils.httpGet(URL);
            data = JSON.parseObject(resp.toString(), new TypeReference<AcibInfo>() {
            });
        }catch (Exception ex){
            log.error("getAcibInfo error, ex:{}", ex.getMessage(), ex);
        }
        return data;
    }

    public static void main(String[] args) {
        Aciba aciba = new Aciba();
        AcibInfo acibInfo = aciba.getAcibInfo();
        System.out.println(acibInfo.getContent());
        System.out.println(acibInfo.getNote());
    }

}