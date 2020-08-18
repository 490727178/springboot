package com.example.springbootmybtisplus.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description 异步线程池
 * @author hemingshun
 * @date 2019/12/19 16:36
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncTaskConfig {

    /**
     核心线程数量
     */
    @Value("${thread.pool.core.pool.size:10}")
    private int threadPoolCorePoolSize;

    /**
     最大线程数
     */
    @Value("${thread.pool.max.pool.size:15}")
    private int threadPoolMaxPoolSize;

    /**
     缓冲队列数
     */
    @Value("${thread.pool.queue.capacity:200}")
    private int threadPoolQueueCapacity;

    /**
     * 任务空闲时间
     */
    @Value("${thread.pool.keep.alive.seconds:60}")
    private int threadPoolKeepAliveSeconds;

    /**
     * 任务等待时间
     */
    @Value("${thread.pool.await.termination.seconds:60}")
    private int threadPoolAwaitTerminationSeconds;

    /*@Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        threadPoolTaskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskScheduler;
    }*/

    /*@Bean("task")
    public Executor task(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        threadPoolTaskScheduler.setThreadNamePrefix("task-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        threadPoolTaskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskScheduler;
    }*/

    @Bean("taskExecutor")
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolCorePoolSize);//核心线程数量，线程池创建时候初始化的线程数
        executor.setMaxPoolSize(threadPoolMaxPoolSize);//最大线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setQueueCapacity(threadPoolQueueCapacity);//缓冲队列，用来缓冲执行任务的队列
        executor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);//当超过了核心线程除之外的线程在空闲时间到达之后会被销毁
        executor.setThreadNamePrefix("taskExecutor-");//设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setAwaitTerminationSeconds(threadPoolAwaitTerminationSeconds);//该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
        log.info("线程池初始化成功");
        return executor;
    }
}
