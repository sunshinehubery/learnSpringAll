package com.example.demo;

import com.example.demo.domain.BlogProperties;
import com.example.demo.domain.ConfigBean;
import com.example.demo.domain.TestConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class,TestConfigBean.class})   //表示启动该配置Bean
//@ImportResource({"classpath:some-application.xml"})  表示若需要使用xml文件配置时需要在入口处引入xml文件
public class DemoApplication {
    @Autowired
    private BlogProperties blogProperties;
    @Autowired
    private ConfigBean configBean;
    @Autowired
    private TestConfigBean testConfigBean;
    @RequestMapping("/")
    public String index(){
        return blogProperties.getName()+"----"+blogProperties.getTitle();
    }
    @RequestMapping("config")
    private String indexConfig(){
        return configBean.getName()+"---"+configBean.getTitle()+"配置中属性相互引用"+configBean.getWholeTitle();
    }
    @RequestMapping("test")
    private String indexTest(){
        return testConfigBean.getName()+"---"+testConfigBean.getAge();
    }
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        //表示关闭启动时图案
        app.setBannerMode(Banner.Mode.OFF);
        //表示不想配置文件被命令行修改，比如打成jar启动时java -jar xxx.jar --server.port=8081，不让其修改配置文件的端口号等
        app.setAddCommandLineProperties(false);
        app.run(args);
    }
}
