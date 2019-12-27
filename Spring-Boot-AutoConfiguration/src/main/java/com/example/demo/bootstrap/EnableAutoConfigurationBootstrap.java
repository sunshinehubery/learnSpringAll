package com.example.demo.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap .class)
                .web(WebApplicationType.NONE)
                .run(args);
        String hello = context.getBean("hello",String.class);
        System.out.println("hello bean:" + hello);
        context.close();
    }
}
