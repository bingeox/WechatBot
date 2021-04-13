package com.bingeox.wechatbot.entity.bot;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/3/31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiQQParam implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;

    /**
     * app_id	  是 int	正整数	1000001	应用标识（AppId）
     * time_stamp 是 int	正整数	1493468759	请求时间戳（秒级）
     * nonce_str  是 string	非空且长度上限32字节	fa577ce340859f9fe	随机字符串
     * sign	      是 string	非空且长度固定32字节		签名信息，详见接口鉴权
     * session	  是 string	UTF-8编码，非空且长度上限32字节	10000	会话标识（应用内唯一）
     * question	  是 string	UTF-8编码，非空且长度上限300字节	你叫啥	用户输入的聊天内容
     */
    private String question;
    @JSONField(name = "app_id")
    private Long appId;
    private String session;
    @JSONField(name = "time_stamp")
    private Long timeStamp;
    @JSONField(name = "nonce_str")
    private String nonceStr;
    private String sign;

    public AiQQParam(String question, Long appId, String session) {
        this.question = question;
        this.appId = appId;
        this.session = session;
        this.timeStamp = System.currentTimeMillis() / 1000L;
        this.nonceStr = UUID.fastUUID().toString();
        this.sign = "";
    }


    public void setReqSign(String appKey) {
        Map<String, Object> map = BeanUtil.beanToMap(this);
        this.sign = getReqSign(map, appKey);
    }

    /**
     * 获取请求签名，接口鉴权 https://ai.qq.com/doc/auth.shtml
     * 计算步骤:
     *     1.将 <key, value> 请求参数对按 key 进行字典升序排序，得到有序的参数对列表 N
     *     2.将列表 N 中的参数对按 URL 键值对的格式拼接成字符串，得到字符串 T（如：key1=value1&key2=value2），
     *         URL 键值拼接过程 value 部分需要 URL 编码，URL 编码算法用大写字母，例如 %E8，而不是小写 %e8
     *     3.将应用密钥以 app_key 为键名，组成 URL 键值拼接到字符串 T 末尾，得到字符串 S（如：key1=value1&key2=value2&app_key = 密钥)
     *     4.对字符串 S 进行 MD5 运算，将得到的 MD5 值所有字符转换成大写，得到接口请求签名
     * 注意事项:
     * 参数名区分大小写，参数值为空不参与签名
     * URL键值拼接过程value部分需要URL编码
     */
    public String getReqSign(Map<String, Object> map, String appKey){
        //将 <key, value> 请求参数对按 key 进行字典升序排序
        LinkedHashMap<String, Object> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

        StringBuffer sb = new StringBuffer();
        sortedMap.forEach((k, v) -> {
            if (v != null && StrUtil.isNotBlank(String.valueOf(v))) {
                sb.append(k + "=" + URLUtil.encodeAll(String.valueOf(v)).toUpperCase()).append("&");
            }
        });
        sb.append("app_key=" + appKey);
        return (MD5.create().digestHex(sb.toString())).toUpperCase();
    }

    public static void main(String[] args) {
        String APP_KEY = "AbdjjKfTouCXKExZ";
        Map<String, Object> map = new HashMap<>();
        map.put("ssdc","1221");
        map.put("ddaw","ad1");
        map.put("ewqf","22");
        map.put("ass","dad");
        map.put("fsas","ada");
        map.put("1sas","ada");
        LinkedHashMap<String, Object> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        StringBuffer sb = new StringBuffer();
        sortedMap.forEach((k, v) -> {
            if (v != null && StrUtil.isNotBlank(String.valueOf(v))) {
                sb.append(k + "=" + URLUtil.encodeAll(String.valueOf(v)).toUpperCase()).append("&");
            }
        });
        sb.append("app_key=" + APP_KEY);
        System.out.println(sb.toString());
    }

}
