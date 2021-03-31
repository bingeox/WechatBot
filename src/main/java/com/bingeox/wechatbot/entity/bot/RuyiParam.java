package com.bingeox.wechatbot.entity.bot;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuyiParam implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    private String q;
    @JSONField(name = "app_key")
    private String appKey;
    @JSONField(name = "user_id")
    private String userId;

}
