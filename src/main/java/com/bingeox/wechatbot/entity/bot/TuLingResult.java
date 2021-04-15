package com.bingeox.wechatbot.entity.bot;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30
 **/
@Data
public class TuLingResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    private Intent intent;
    private List<Results> results;

    @Data
    public static class Intent implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;
        private int code;
    }

    @Data
    public static class Results implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;
        /**
         * ‘组’编号:0为独立输出，大于0时可能包含同组相关内容 (如：音频与文本为一组时说明内容一致)
         */
        private int groupType;
        /**
         * 文本(text);连接(url);音频(voice);视频(video);图片(image);图文(news)
         */
        private String resultType;
        private Values values;

        @Data
        public static class Values implements Serializable {
            @JsonProperty(value = "text")
            @JsonAlias(value = {"url", "voice", "video", "image", "news"})
            private String value;
        }

    }

}
