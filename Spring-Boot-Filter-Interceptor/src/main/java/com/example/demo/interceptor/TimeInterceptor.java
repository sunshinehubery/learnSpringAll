package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("处理拦截之前");
        request.setAttribute("startTime",new Date().getTime());
        System.out.println(handler.getClass().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("开始处理拦截器");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("拦截器耗时：" + (new Date().getTime() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理拦截器之后");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("拦截器耗时：" + (new Date().getTime() - startTime));
        System.out.println("异常信息：" + ex);
    }
}
