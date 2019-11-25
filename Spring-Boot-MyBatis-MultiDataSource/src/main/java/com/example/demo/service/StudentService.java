package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Map<String,Object>> getAllStudentsFromMysql();
    List<Map<String,Object>> getAllStudentsFromOracle();
}
