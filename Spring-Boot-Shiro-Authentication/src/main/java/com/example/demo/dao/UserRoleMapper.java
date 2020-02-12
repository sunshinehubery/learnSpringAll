package com.example.demo.dao;

import com.example.demo.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/2/11 14:16
 * @Version: 1.0
 **/
@Mapper
public interface UserRoleMapper {
    List<Role> findRolesByUserName(String username);
}
