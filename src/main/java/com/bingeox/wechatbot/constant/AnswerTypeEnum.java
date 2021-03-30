package com.bingeox.wechatbot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/30
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AnswerTypeEnum {

    TXT(5000, "文本");

    private int type;
    private String desc;
}
