package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class TestController {
    @GetMapping("/test")
    public String test(@NotBlank(message = "{required}") String name,
                       @Email(message = "{invalid}") String email){
        return "success";
    }
    @GetMapping("/test123")
    public String test123(@Valid User user){
        return "success";
    }
}
