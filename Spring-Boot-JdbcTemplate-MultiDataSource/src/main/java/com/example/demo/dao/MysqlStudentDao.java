package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface MysqlStudentDao {
    List<Map<String,Object>> getAllStudents();
}
