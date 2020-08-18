package com.example.springbootmybtisplus.async;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @Author: zengrenshang
 * @Date: 2020/4/29 9:27
 */
@SuppressWarnings("ALL")
@Component
@Slf4j
public class AsyncDemo {

//    /**
//     * 最简单的异步调用，返回值为void
//     */
//    @Async("taskExecutor")
//    public void asyncInvokeSimplest() {
//        log.info("ThreadName={}", Thread.currentThread().getName());
//        log.info("asyncSimplest");
//    }
//
//    /**
//     * 带参数的异步调用 异步方法可以传入参数
//     *
//     * @param s
//     */
//    @Async("taskExecutor")
//    public void asyncInvokeWithParameter(String s) {
//        log.info("ThreadName={}", Thread.currentThread().getName());
//        log.info("asyncInvokeWithParameter, parementer={}", s);
//    }
//
//    /**
//     * 异常调用返回Future
//     *
//     * @param i
//     * @return
//     */
//    @Async("taskExecutor")
//    public Future<String> asyncInvokeReturnFuture(int i) {
//        log.info("asyncInvokeReturnFuture, parementer={}", i);
//        log.info("ThreadName={}", Thread.currentThread().getName());
//        Future<String> future;
//        try {
//            Thread.sleep(1000 * 1);
//            future = new AsyncResult<String>("success:" + i);
//        } catch (InterruptedException e) {
//            future = new AsyncResult<String>("error");
//        }
//        return future;
//    }


    @Autowired
    private RestTemplate restTemplate; // spring提供一个http调用工具

    /**
     * 调用获取用户基础信息的http接口
     */
    @Async("taskExecutor")
    public ListenableFuture<JSONObject> getUserInfo(String userId) {
        System.out.println(Thread.currentThread().getName());
        // 1. 先从调用获取用户基础信息的http接口
        long userinfoTime = System.currentTimeMillis();
        String value = restTemplate.getForObject("http://127.0.0.1/userinfo-api/get?userId=" + userId, String.class);
        JSONObject userInfo = JSONObject.parseObject(value);
        System.out.println("userinfo-api用户基本信息接口调用时间为" + (System.currentTimeMillis() - userinfoTime) + "毫秒");
        return new AsyncResult<>(userInfo);
    }

    /**
     * 用获取用户积分信息的接口
     *
     * @return
     */
    @Async("taskExecutor")
    public ListenableFuture<JSONObject> getIntergralInfo(String userId) {
        System.out.println(Thread.currentThread().getName());
        // 2. 再调用获取用户积分信息的接口
        long integralApiTime = System.currentTimeMillis();
        String intergral = restTemplate.getForObject("http://127.0.0.1/integral-api/get?userId=" + userId,
                String.class);
        JSONObject intergralInfo = JSONObject.parseObject(intergral);
        System.out.println("integral-api积分接口调用时间为" + (System.currentTimeMillis() - integralApiTime) + "毫秒");
        return new AsyncResult<>(intergralInfo);
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //获取开始时间
        String str1 = "aa aa aaaa aaa a a a aa aa a";
        String[] str2 = str1.split(" ");
        int max = str2[0].length();
        for (int i = 1; i < str2.length; i++) {
            if (max < str2[i].length()) {
                max = str2[i].length();
            }
        }
        System.out.println(max);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        // 得到JVM中的空闲内存量（单位是字节）
        System.out.println("jvm所剩内存：" + Runtime.getRuntime().freeMemory());

        int num = 0, res = 0;
        for (char c : str1.toCharArray()) {
            if (c == ' ') {
                res = Math.max(res, num);
                num = 0;
            } else {
                num++;
            }
        }
        System.out.println(res);
    }

}