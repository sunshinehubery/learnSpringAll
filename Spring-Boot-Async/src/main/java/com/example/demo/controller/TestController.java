package com.example.demo.controller;

import com.example.demo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @GetMapping("/async")
    public String asyncMethod() throws Exception{
        Long start = System.currentTimeMillis();
        logger.info("异步方法开始：");
        Future<String> stringFuture = testService.asyncMethod();
//        try {
//            String result = stringFuture.get(10, TimeUnit.SECONDS);   get中可以设置超时时长，超出抛出超时异常
//            logger.info("异步方法的返回值：{}", result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        String result = stringFuture.get();
        logger.info("异步方法的返回值：{}", result);
        logger.info("异步方法结束：");
        Long end = System.currentTimeMillis();
        Long times = end - start;
        logger.info("异步方法总耗时:{}ms", times);
        return times.toString();
    }

    @GetMapping("sync")
    public String syncMethod(){
        Long start = System.currentTimeMillis();
        logger.info("同步方法开始：");
        testService.syncMethod();
        logger.info("同步方法结束：");
        Long end = System.currentTimeMillis();
        Long times = end - start;
        logger.info("同步方法总耗时:{}ms", times);
        return times.toString();
    }
}
