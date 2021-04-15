package com.bingeox.wechatbot.control.onewords;

import cn.hutool.core.util.StrUtil;
import com.bingeox.wechatbot.entity.RetModel;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description 从土味情话中获取每日一句。
 * @since 2021/4/8
 **/
@Slf4j
@Component
public class LoveLive implements Oneword {
    private static final String URL = "https://api.lovelive.tools/api/SweetNothings";

    @Override
    public RetModel getOneword() {
        String info = getLoveLiveInfo();
        if (StrUtil.isNotBlank(info)) {
            return RetModel.success(info);
        }
        return RetModel.fail();
    }

    public String getLoveLiveInfo() {
        String lovelive = "";
        try {
            lovelive = HttpClientUtils.httpGet_Str(URL);
        } catch (Exception ex) {
            log.error("getLoveLiveInfo error, ex:{}", ex.getMessage(), ex);
        }
        return lovelive;
    }

    public static void main(String[] args) {
        LoveLive loveLive = new LoveLive();
        System.out.println(loveLive.getLoveLiveInfo());
    }
}
