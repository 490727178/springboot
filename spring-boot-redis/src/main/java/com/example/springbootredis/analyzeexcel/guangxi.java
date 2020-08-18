package com.example.springbootredis.analyzeexcel;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zengrenshang
 * @Date: 2020/5/16 9:55
 */
@Component
public class guangxi implements Analysis {
    @Override
    public String areaCodePrefix() {
        return "04";
    }

    @Override
    public Map<String, Object> certificatesType() {
        Map<String,Object> map = new ConcurrentHashMap<>(16);
        map.put("广西证件类型","04");
//        map.put("居民身份证","01");
//        map.put("临时身份证","02");
//        map.put("军人身份证","03");
//        map.put("护照","04");
//        map.put("户口簿","05");
//        map.put("营业执照","06");
//        map.put("驾驶执照","07");
//        map.put("法人代码证","08");
//        map.put("武装警察身份证","09");
//        map.put("外交人员身份证","10");
//        map.put("外国人居留许可证","11");
//        map.put("边民出入境通行证","12");
//        map.put("港澳居民来往内地通行证-香港","13");
//        map.put(" 港澳居民来往内地通行证-澳门","14");
//        map.put("台湾居民来往大陆通行证","15");
//        map.put("纳税人识别号（TIN）","16");
//        map.put("其他","99");
        return map;
    }

    @Override
    public Map<String, Object> bankType() {
        Map<String,Object> map = new ConcurrentHashMap<>(16);
        map.put("广西银行类型","04");
        return map;
    }
}
