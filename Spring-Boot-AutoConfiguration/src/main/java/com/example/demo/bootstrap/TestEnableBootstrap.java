package com.example.demo.bootstrap;

import com.example.demo.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableHelloWorld
public class TestEnableBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(TestEnableBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String hello = context.getBean("hello", String.class);
        System.out.println("hello Bean: " + hello);
        context.close();
    }
}
