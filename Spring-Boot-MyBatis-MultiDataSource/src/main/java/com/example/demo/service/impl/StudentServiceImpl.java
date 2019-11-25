package com.example.demo.service.impl;

import com.example.demo.mysqldao.MysqlStudentMapper;
import com.example.demo.oracledao.OracleStudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MysqlStudentMapper mysqlStudentMapper;
    @Autowired
    private OracleStudentMapper oracleStudentMapper;
    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql() {
        return this.mysqlStudentMapper.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStudentsFromOracle() {
        return this.oracleStudentMapper.getAllStudents();
    }
}
