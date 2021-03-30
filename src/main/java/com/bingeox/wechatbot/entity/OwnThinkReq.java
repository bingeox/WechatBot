package com.bingeox.wechatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnThinkReq implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    private String spoken;
    private String appid;
    private String userid;

}
