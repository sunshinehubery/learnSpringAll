package com.example.demo.controller;

import com.example.demo.bean.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

@Controller
public class TestController {
    @Autowired
    private ObjectMapper objectMapper;

    @JsonView(User.UserNameView.class)
    @RequestMapping("getUser")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setUserName("sunshine");
        user.setAge(18);
        user.setBirthday(new Date());
        return user;
    }
    @RequestMapping("serializable")
    @ResponseBody
    public String serializable(){
        try {
            User user = new User();
            user.setUserName("sunshine");
            user.setAge(18);
            user.setBirthday(new Date());
            String str = objectMapper.writeValueAsString(user);
            return  str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("readjsonasobject")
    @ResponseBody
    public String readJsonAsObject(){
        try {
            String json = "{\"userName\":\"sunshine\",\"age\":18}";
            User user = objectMapper.readValue(json, User.class);
            String name = user.getUserName();
            int age = user.getAge();
            return name + " " + age;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
