package com.example.demo.service;

import com.example.demo.bean.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "student")
public interface StudentService {
    //真实调用函数，用于新增和修改，修改时更新缓存的数据是用"#p0.sno"用于更新，否则查询到的是脏数据，所以返回的是Student对象
    @CachePut(key = "#p0.sno")
    Student update(Student student);

    //用于对缓存中的数据进行删除，allEntries为true会删除所有的数据
    @CacheEvict(key = "#p0", allEntries = true)
    void deleteStudentBySno(String sno);

    @Cacheable(key = "#p0")
    Student queryStudentBySno(String sno);
}
