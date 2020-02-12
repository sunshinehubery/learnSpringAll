package com.example.demo.dao;

import com.example.demo.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/2/11 14:19
 * @Version: 1.0
 **/
@Mapper
public interface UserPermissionMapper {
    List<Permission> findPermissionsByUserName(String username);
}
