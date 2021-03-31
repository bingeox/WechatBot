package com.bingeox.wechatbot.entity.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "q")
    private String req;
    @JsonProperty(value = "app_key")
    private String appKey;
    @JsonProperty(value = "user_id")
    private String userId;

}
