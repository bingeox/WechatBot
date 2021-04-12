package com.bingeox.wechatbot.control.onewords;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingeox.wechatbot.constant.Constants;
import com.bingeox.wechatbot.entity.RetModel;
import com.bingeox.wechatbot.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiaobing@meicai.cn
 * @description https://github.com/MZCretin/RollToolsApi#随机获取笑话段子列表
 * 随机获取笑话段子列表
 * @since 2021/4/8
 **/
@Slf4j
@Component
public class Rtjokes implements Oneword {
    private static final String URL = "https://www.mxnzp.com/api/jokes/list/random?app_id=qcqfdpg8v0ako9pq&app_secret=WGFKK3FjbDdDMXc5T2l3Q2RFSmpxQT09";

    @Override
    public RetModel getOneword() {
        String info = getRtjokesInfo();
        if (StrUtil.isNotBlank(info)){
            return RetModel.success(info);
        }
        return RetModel.fail();
    }

    public String getRtjokesInfo(){
        String rtjokes = "";
        try {
            JSONObject resp = HttpClientUtils.httpGet(URL);
            if (resp.getIntValue("code") == Constants.ONE){
                JSONArray data = resp.getJSONArray("data");
                rtjokes = JSON.parseObject(data.get(0).toString()).getString("content");
            }
        }catch (Exception ex){
            log.error("getRtjokesInfo error, ex:{}", ex.getMessage(), ex);
        }
        return rtjokes;
    }

    public static void main(String[] args) {
        Rtjokes rtjokes = new Rtjokes();
        System.out.println(rtjokes.getRtjokesInfo());
    }
}
