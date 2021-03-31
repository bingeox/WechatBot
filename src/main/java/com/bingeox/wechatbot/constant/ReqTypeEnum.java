package com.bingeox.wechatbot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author bingeox
 * @description
 * @since 2021/3/31 0031
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ReqTypeEnum {

    TEXT(0, "文本"),
    IMAGE(1, "图片"),
    MEDIA(2, "音频");
    private int type;
    private String desc;

}
