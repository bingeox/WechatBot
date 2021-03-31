package com.bingeox.wechatbot.entity.bot;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bingeox
 * @description
 * @since 2021/3/30
 **/
@Data
public class RuyiResult implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    private List<Intent> intents;

    @Data
    public static class Intent implements Serializable {
        private static final long serialVersionUID = -3404115733111557203L;

        private List<Output> outputs;

        @Data
        public static class Output implements Serializable {
            private static final long serialVersionUID = -3404115733111557203L;

            private String type;

            private Property property;

            @Data
            public static class Property implements Serializable {
                private static final long serialVersionUID = -3404115733111557203L;

                private String text;
            }

        }
    }

}
