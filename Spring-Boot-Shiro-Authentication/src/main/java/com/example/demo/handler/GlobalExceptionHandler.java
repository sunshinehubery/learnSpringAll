package com.example.demo.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @description: 全局捕获异常类
 * @author: sunshinehubery
 * @date: 2020/2/11 16:05
 * @Version: 1.0
 **/
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AuthorizationException.class)
    public String handleAuthorizationException(){
        return "403";
    }
}
