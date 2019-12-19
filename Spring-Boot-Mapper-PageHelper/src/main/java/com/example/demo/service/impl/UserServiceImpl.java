package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Repository;

@Repository("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {
}
