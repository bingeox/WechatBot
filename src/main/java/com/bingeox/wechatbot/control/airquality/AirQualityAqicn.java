//package com.bingeox.wechatbot.control.airquality;
//
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.bingeox.wechatbot.constant.AirStatusEnum;
//import com.bingeox.wechatbot.utils.HttpClientUtils;
//import com.sun.deploy.net.HttpUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.reactive.function.client.WebClient;
//
///**
// * @description 获取空气质量
// * 官网：http://aqicn.org/here/
// * token，申请地址：http://aqicn.org/data-platform/token/#/
// * @since 2021/3/30
// **/
//@Slf4j
//public class AirQualityAqicn {
//
//    private static final String AQICN_TOKEN = "6382db85ef321ae81f316486de0b5b8aa6c84f62";
//
//    public static String getAirQuality(String city){
//
//        if (StrUtil.isBlank(city)){
//            return AirStatusEnum.SUPER.getStatus();
//        }
//        log.info("获取 {} 的空气质量...", city);
//
//        String url = "http://api.waqi.info/feed/"+ city +"/?token=" + AQICN_TOKEN;
//        try{
//
//            JSONObject resp = HttpClientUtils.httpGet(url);
//            if resp.status_code == 200:
//                # print(resp.text)
//            content_dict = resp.json()
//            if content_dict.get('status') == 'ok':
//            data_dict = content_dict['data']
//            aqi = data_dict['aqi']
//            air_status = '严重污染'
//            for key in sorted(AIR_STATUS_DICT):
//            if key >= aqi:
//            air_status = AIR_STATUS_DICT[key]
//            break
//                    aqi_info = '{city} PM2.5：{aqi} {air_status}'.format(city=city, aqi=aqi, air_status=air_status)
//                    # print(aqi_info)
//            return aqi_info
//                else:
//            print('获取空气质量失败:{}'.format(content_dict['data']))
//            return None
//            print('获取空气质量失败。')
//        }catch (Exception ex){
//            print(str(exception))
//        }
//        return None
//    }
//}
//
//        if __name__ == '__main__':
//        city = '长沙'
//        dd = get_air_quality(city)
//        print(dd)
