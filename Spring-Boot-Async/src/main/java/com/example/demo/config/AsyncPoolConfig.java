package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);  //设置线程池的核心线程数量，默认为1
        executor.setMaxPoolSize(200);  //线程的维护的最大线程数量
        executor.setQueueCapacity(25);  // 缓存队列数量
        executor.setKeepAliveSeconds(200);  // 非核心线程的线程的存活时间，默认60s
        executor.setThreadNamePrefix("asyncThread");  // 线程的前缀名称
        executor.setWaitForTasksToCompleteOnShutdown(true); //是否等待全部线程执行完成才关闭线程池，默认false
        executor.setAwaitTerminationSeconds(60);  // 等待时长，默认不等待
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  // 处理当线程没有时的处理策略，默认是抛出异常abortPolicy
        // 直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务 -- CallerRunsPolicy
        executor.initialize();  // 初始化
        return executor;
    }
}
