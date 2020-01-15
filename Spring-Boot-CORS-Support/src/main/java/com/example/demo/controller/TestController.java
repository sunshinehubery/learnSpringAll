package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/hello")
    @ResponseBody
//    @CrossOrigin(value = "*")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
