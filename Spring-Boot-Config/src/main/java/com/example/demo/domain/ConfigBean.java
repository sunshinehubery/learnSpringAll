package com.example.demo.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

//若属性多时，可配置一个相应的Bean
@ConfigurationProperties(prefix = "sunshine.blog")
public class ConfigBean {
    private String name;
    private String title;
    private String wholeTitle;

    public String getWholeTitle() {
        return wholeTitle;
    }

    public void setWholeTitle(String wholeTitle) {
        this.wholeTitle = wholeTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
