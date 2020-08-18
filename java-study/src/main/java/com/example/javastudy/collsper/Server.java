package com.example.javastudy.collsper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: zengrenshang
 * @Date: 2020/5/9 14:36
 */
@Service
public class Server {
    class Request {
        String code;
        CompletableFuture<Map<String, Object>> future;
    }

    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue();


    @PostConstruct
    public void init() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {

            int size = queue.size();
            if (size == 0) {
                return;
            }
            System.out.println("合并请求size=" + size);
            ArrayList<Request> requests = new ArrayList<>();
            ArrayList<String> codes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Request poll = queue.poll();
                requests.add(poll);
                codes.add(poll.code);
            }
            List<Map<String, Object>> responses = dao.queryCommodityByCodeBatch(codes);

            HashMap<String, Map<String, Object>> responseMap = new HashMap<>();
            responses.forEach(map -> {
                String code = map.get("code").toString();
                responseMap.put(code, map);
            });
            requests.forEach(request -> {
                // 根据请求中携带的能表示唯一参数，去批量查询的结果中找响应
                Map<String, Object> result = responseMap.get(request.code);
                // 将结果返回到对应的请求线程
                request.future.complete(result);
            });
        }, 0, 10, TimeUnit.MICROSECONDS);
    }

    @Autowired
    Dao dao;

    // 1000 用户请求，1000个线程
    public Map<String, Object> queryCommodity(String movieCode) throws ExecutionException, InterruptedException {
        // 1000次 怎么样才能变成  更少的接口
        // 思路： 将不同用户的同类请求合并起来
        // 并非立刻发起接口调用，请求 收集起来，再进行
        Request request = new Request();
        request.code = movieCode;
        // 异步编程： 获取异步处理的结果
        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        request.future = future;
        queue.put(request);
        return future.get(); // 此处get方法，会阻塞线程运行，直到future有返回
        // 什么时候返回结果？ 批量查询之后。 怎么进行等待
        // return queryServiceRemoteCall.queryMovieInfoByCode(movieCode);
    }


}
