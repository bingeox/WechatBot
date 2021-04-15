package com.bingeox.wechatbot.control.onewords;

import cn.hutool.core.util.StrUtil;
import com.bingeox.wechatbot.entity.RetModel;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description 彩虹屁生成器
 * https://chp.shadiao.app/?from_nmsl
 * @since 2021/4/8
 **/
@Slf4j
@Component
public class Caihongpi implements Oneword {
    private static final String URL = "https://chp.shadiao.app/api.php";

    public String getCaihongpiInfo() {
        String caihongpi = "";
        try {
            caihongpi = HttpClientUtils.httpGet_Str(URL);
        } catch (Exception ex) {
            log.error("getCaihongpiInfo error, ex:{}", ex.getMessage(), ex);
        }
        return caihongpi;
    }

    public static void main(String[] args) {
        Caihongpi caihongpi = new Caihongpi();
        System.out.println(caihongpi.getCaihongpiInfo());
    }

    @Override
    public RetModel getOneword() {
        String info = getCaihongpiInfo();
        if (StrUtil.isNotBlank(info)) {
            return RetModel.success(info);
        }
        return RetModel.fail();
    }
}
