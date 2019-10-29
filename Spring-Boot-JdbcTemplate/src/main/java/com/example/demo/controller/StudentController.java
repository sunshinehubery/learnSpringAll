package com.example.demo.controller;

import com.example.demo.bean.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/queryStudent",method = RequestMethod.GET)
    public Student queryStudentBySno(String sno){
        return this.studentService.queryStudentBySno(sno);
    }
    @RequestMapping(value = "/queryAllList")
    public List<Map<String,Object>> queryAllList(){
        return this.studentService.queryStudentsListMap();
    }
    @RequestMapping(value = "/addStudent",method = RequestMethod.GET)
    public int saveStudent(String sno,String sname,String ssex){
        Student student = new Student();
        student.setSno(sno);
        student.setSname(sname);
        student.setSsex(ssex);
        return this.studentService.add(student);
    }
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
    public int deleteBySno(String sno){
        return this.studentService.deleteBySno(sno);
    }
}
