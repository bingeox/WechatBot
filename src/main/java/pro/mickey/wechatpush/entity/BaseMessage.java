package pro.mickey.wechatpush.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseMessage implements Serializable {
    private static final long serialVersionUID = -3404115733111557203L;
    private int type;
    private Object content;
    private String id;
    private String wxid;
    private String receiver;
    private String sender;
    private String srvid;
    private String time;
    private String status;

    public String toJson() {
        return JSON.toJSONString(this);
    }

}
