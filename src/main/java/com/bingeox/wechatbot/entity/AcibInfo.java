package com.bingeox.wechatbot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/8
 **/
@Data
public class AcibInfo implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    /**
     * {
     * "sid": "4091",
     * "tts": "https://staticedu-wps.cache.iciba.com/audio/46d52422295d48414dca1f17acc18b63.mp3",
     * "content": "The deepest principle in human nature is the craving to be appreciated.",
     * "note": "人性中最深刻的本能就是对被欣赏的渴望。",
     * "love": "0",
     * "translation": "新版每日一句",
     * "picture": "https://staticedu-wps.cache.iciba.com/image/5672a8df119ddc9fa2d7ac6a1e7d3f0e.jpg",
     * "picture2": "https://staticedu-wps.cache.iciba.com/image/6050344edfd2490aeb505e3011edd794.jpg",
     * "caption": "词霸每日一句",
     * "dateline": "2021-04-08",
     * "s_pv": "0",
     * "sp_pv": "0",
     * "fenxiang_img": "https://staticedu-wps.cache.iciba.com/image/862be4187487f8ec9d0f031bb848149f.png",
     * "picture3": "https://staticedu-wps.cache.iciba.com/image/782c5e8fb98bb3d336da440dc8ad0b0d.jpg",
     * "picture4": "https://staticedu-wps.cache.iciba.com/image/ad0d806851247b68cbee716d0d88dc0a.jpg",
     * "tags": []
     * }
     */
    private String tts;
    private String content;
    private String note;
    @JSONField(name = "fenxiang_img")
    private String fenxiangImg;

}
