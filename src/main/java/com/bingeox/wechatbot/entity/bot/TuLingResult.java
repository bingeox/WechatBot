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
        /**
         * 5000	无解析结果
         * 6000	暂不支持该功能
         * 4000	请求参数格式错误
         * 4001	加密方式错误
         * 4002	无功能权限
         * 4003	该apikey没有可用请求次数
         * 4005	无功能权限
         * 4007	apikey不合法
         * 4100	userid获取失败
         * 4200	上传格式错误
         * 4300	批量操作超过限制
         * 4400	没有上传合法userid
         * 4500	userid申请个数超过限制
         * 4600	输入内容为空
         * 4602	输入文本内容超长(上限150)
         * 7002	上传信息失败
         * 8008	服务器错误
         * 0	上传成功
         */
        private int code;
    }

    @Data
    public static class Results implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;
        /**
         *‘组’编号:0为独立输出，大于0时可能包含同组相关内容 (如：音频与文本为一组时说明内容一致)
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
