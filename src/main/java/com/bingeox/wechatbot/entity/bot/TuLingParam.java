package com.bingeox.wechatbot.entity.bot;

import com.bingeox.wechatbot.constant.ReqTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30
 **/
@Data
@NoArgsConstructor
public class TuLingParam implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    /**
     * 输入类型:0-文本(默认)、1-图片、2-音频
     */
    private int reqType;
    /**
     * 输入信息 输入参数必须包含inputText或inputImage或inputMedia！
     */
    private Perception perception;
    /**
     * 用户信息
     */
    private UserInfo userInfo;

    public TuLingParam(String req, String apiKey, String userId, ReqTypeEnum reqType){
        this.reqType = reqType.getType();
        this.userInfo = new UserInfo(apiKey, userId);
        Perception perception = new Perception();
        Perception.Resource resource = new Perception.Resource();
        if (reqType.equals(ReqTypeEnum.TEXT)){
            perception.setInputText(resource.setText(req));
        }
        if (reqType.equals(ReqTypeEnum.IMAGE)){
            perception.setInputImage(resource.setUrl(req));
        }
        if (reqType.equals(ReqTypeEnum.MEDIA)){
            perception.setInputMedia(resource.setUrl(req));
        }
    }

    @Data
    public static class Perception implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;
        /**
         * 文本信息
         */
        private Resource inputText;
        /**
         * 图片信息
         */
        private Resource inputImage;
        /**
         * 音频信息
         */
        private Resource inputMedia;

        @Data
        @Accessors(chain = true)
        public static class Resource implements Serializable {
            private static final long serialVersionUID = -3404115733111557203L;
            private String text;
            private String url;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;
        private String apiKey;
        private String userId;
    }

}
