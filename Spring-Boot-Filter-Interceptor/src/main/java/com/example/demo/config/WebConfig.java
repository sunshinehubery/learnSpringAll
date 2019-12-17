package com.example.demo.config;

import com.example.demo.filter.TimeFilter;
import com.example.demo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;
// 使用FilterRegistrationBean来注册过滤器
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private TimeInterceptor timeInterceptor;
    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter(){
        FilterRegistrationBean<TimeFilter> filterRegistrationBean = new FilterRegistrationBean<TimeFilter>();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
