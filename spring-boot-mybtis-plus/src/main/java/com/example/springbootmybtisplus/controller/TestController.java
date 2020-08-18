package com.example.springbootmybtisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybtisplus.async.AsyncDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;



/**
 * @Author: zengrenshang
 * @Date: 2020/4/29 9:47
 */
@RestController
public class TestController {
    @Autowired
    private AsyncDemo asyncDemo;

    @GetMapping("test")
    public Object getInfo(String userId) throws Exception {
        System.out.println(Thread.currentThread().getName());
        ListenableFuture<JSONObject> userInfo = asyncDemo.getUserInfo(userId);
        ListenableFuture<JSONObject> intergralInfo = asyncDemo.getIntergralInfo(userId);
        JSONObject result = new JSONObject();
        result.putAll(userInfo.get()); // FutureTask.get 获取一个多线程异步执行的结果，没有执行完毕则等待结果
        result.putAll(intergralInfo.get());

        return result;
    }


    @GetMapping("user")
    public  String  queryUser(){
        return "get请求";
    }
    @PostMapping("user")
    public  String  saveUser(){
        return "post请求";
    }
    @PutMapping("user")
    public  String  updateUser(){
        return "update请求";
    }
    @DeleteMapping("user")
    public  String  deleteUser(){
        return "delete请求";
    } 



}
