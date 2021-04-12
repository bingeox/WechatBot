package com.bingeox.wechatbot.control.onewords;

import cn.hutool.core.util.StrUtil;
import com.bingeox.wechatbot.entity.RetModel;
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
public class Hitokoto implements Oneword {
    private static final String URL = "https://v1.hitokoto.cn/?encode=text";

    @Override
    public RetModel getOneword() {
        String info = getHitokotoInfo();
        if (StrUtil.isNotBlank(info)){
            return RetModel.success(info);
        }
        return RetModel.fail();
    }

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
