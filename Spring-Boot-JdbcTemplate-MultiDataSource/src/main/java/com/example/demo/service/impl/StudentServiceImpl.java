package com.example.demo.service.impl;

import com.example.demo.dao.MysqlStudentDao;
import com.example.demo.dao.OracleStudentDao;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MysqlStudentDao mysqlStudentDao;
    @Autowired
    private OracleStudentDao oracleStudentDao;
    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql() {
        return this.mysqlStudentDao.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStudentsFromOracle() {
        return this.oracleStudentDao.getAllStudents();
    }
}
