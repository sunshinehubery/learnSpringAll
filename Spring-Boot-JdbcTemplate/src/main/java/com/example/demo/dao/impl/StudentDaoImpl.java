package com.example.demo.dao.impl;

import com.example.demo.bean.Student;
import com.example.demo.dao.StudentDao;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int add(Student student) {
        String sql = "insert into student(sno,sname,ssex) values(:sno,:sname,:ssex)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int update(Student student) {
        String sql = "update student set sname = ?,ssex = ? where sno = ?";
        Object[] args = {student.getSname(),student.getSsex(),student.getSno()};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        return this.jdbcTemplate.update(sql,args,argTypes);
    }

    @Override
    public int deleteBySno(String sno) {
        String sql = "delete from student where sno = ?";
        Object[] arg = {sno};
        int[] argType = {Types.VARCHAR};
        return this.jdbcTemplate.update(sql,arg,argType);
    }

    @Override
    public List<Map<String, Object>> queryStudentsListMap() {
        String sql = "select * from student";
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        String sql = "select * from student where sno = ?";
        Object[] arg = {sno};
        int[] argType = {Types.VARCHAR};
        List<Student> studentList = this.jdbcTemplate.query(sql, arg, argType, new StudentMapper());
        if(studentList != null && studentList.size() > 0){
            return studentList.get(0);
        }else {
            return null;
        }
    }
}
