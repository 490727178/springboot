package com.example.javastudy.collsper;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: zengrenshang
 * @Date: 2020/5/9 14:53
 */
@Component
public class Dao {

    /**
     * 批量查询 - 调用远程的商品信息查询接口
     *
     * @param codes 多个商品编码
     * @return 返回多个商品信息
     */
    public List<Map<String, Object>> queryCommodityByCodeBatch(List<String> codes) {
        // 不支持批量查询 http://moviewapi.com/query.do?id=10001     --> {code:10001, star：xxxx.....}
        // http://moviewapi.com/query.do?ids=10001,10002,10003,10004   --> [{code:10001, star///}, {...},{....}]
        List<Map<String, Object>> result = new ArrayList<>();
        for (String code : codes) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("commodityId", new Random().nextInt(999999999));
            hashMap.put("code", code);
            hashMap.put("phone", "huawei");
            hashMap.put("isOk", "true");
            hashMap.put("price","4000");
            result.add(hashMap);
        }
        return result;
    }
}
