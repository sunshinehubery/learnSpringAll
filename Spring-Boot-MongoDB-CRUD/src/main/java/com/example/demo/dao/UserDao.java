package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends MongoRepository<User,String> {

    // 根据年龄段查询 (from,to)
    List<User> findByAgeBetween(Integer from, Integer to);

    //根据name字段查询
    List<User> findByNameEquals(String name);

    //根据description字段模糊查询
    List<User> findByDescriptionIsLike(String description);

    //通过年龄段，姓名和描述的模糊插叙查询用户
    List<User> findByNameEqualsAndAgeBetweenAndDescriptionIsLike(Integer from, Integer to, String name, String description);
}
