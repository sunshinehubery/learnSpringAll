package com.example.demo.service;

import com.example.demo.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    List<Map<String,Object>> queryStudentsListMap();
    Student queryStudentBySno(String sno);
}
