package com.bingeox.wechatbot.control.onewords;

import com.bingeox.wechatbot.entity.RetModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author xiaobing@meicai.cn
 * @description
 * @since 2021/4/12
 **/
@Component
public class OnewordFactory {
    @Autowired
    private Aciba aciba;
    @Autowired
    private Caihongpi caihongpi;
    @Autowired
    private Hitokoto hitokoto;
    @Autowired
    private LoveLive loveLive;
    @Autowired
    private Rtjokes rtjokes;

    private static List<Oneword> onewords;

    @PostConstruct
    public void initOne() {
        onewords = Lists.newLinkedList();
        onewords.add(aciba);
        onewords.add(caihongpi);
        onewords.add(hitokoto);
        onewords.add(loveLive);
        onewords.add(rtjokes);
    }

    public String getOneword() {
        String oneword = "";
        for (Oneword one : onewords) {
            RetModel model = one.getOneword();
            if (model.isRet()) {
                oneword = model.getMessage();
                break;
            }
        }
        return oneword;
    }


}
