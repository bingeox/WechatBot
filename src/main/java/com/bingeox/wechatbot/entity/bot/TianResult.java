package com.bingeox.wechatbot.entity.bot;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/31
 **/
@Data
public class TianResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    /**
     * 回答内容，datatype为view时返回一个列表对象
     */
    private String reply;
    /**
     * 数据类型：text文本；view图文；image图片
     */
    @JSONField(name = "datatype")
    private String dataType;

}
