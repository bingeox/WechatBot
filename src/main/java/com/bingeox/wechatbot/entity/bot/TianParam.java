package com.bingeox.wechatbot.entity.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/31
 **/
@Data
@NoArgsConstructor
public class TianParam implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    /**
     * key	string	是	您自己的APIKEY（注册账号后获得）	API密钥
     * question	string	是	robot	提问（建议urlencode）
     * uniqueid	string	否	a36586d752193ea6	用户唯一身份ID，方便上下文关联
     * mode	int	否	0	工作模式，宽松模式0（回答率高）、精确模式1（相关性高）、私有模式2（只从私有词库中回答）
     * priv	int	否	0	私有词库匹配模式，完整匹配0[默认]、智能匹配1，模糊匹配2，结尾匹配3，开头匹配4
     * restype	int	否	0	输入类型，文本0、语音1、人脸图片2
     */
    @JsonProperty(value = "key")
    private String key;
    private String question;
    @JsonProperty(value = "uniqueid")
    private String uniqueId;
    private int restype;
    private int mode;

    public TianParam(String question, String key, String uniqueId, int restype) {
        this.key = key;
        this.question = question;
        this.uniqueId = uniqueId;
        this.restype = restype;
        this.mode = 0;
    }

}
