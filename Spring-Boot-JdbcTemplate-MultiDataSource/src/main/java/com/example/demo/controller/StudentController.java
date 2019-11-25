package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/queryStudentFromMysql")
    public List<Map<String,Object>> queryStudentFromMysql(){
        return this.studentService.getAllStudentsFromMysql();
    }
    @RequestMapping("/queryStudentFromOracle")
    public List<Map<String,Object>> queryStudentFromOracle(){
        return this.studentService.getAllStudentsFromOracle();
    }
}
