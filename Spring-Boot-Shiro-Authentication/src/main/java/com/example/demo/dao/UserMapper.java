package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/1/18 21:30
 * @Version: 1.0
 **/
@Mapper
public interface UserMapper {
    User findByUserName(String username);
}
