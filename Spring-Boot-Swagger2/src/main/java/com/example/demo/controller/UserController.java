package com.example.demo.controller;

import com.example.demo.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户controller")
@RestController
@RequestMapping("user")
public class UserController {
    @ApiOperation(value = "获取用户信息", notes = "根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping("/{id}")
    public User getUserById(@PathVariable(value = "id")Long id){
        User user = new User();
        user.setId(id);
        user.setName("sunshine");
        user.setAge(18);
        return user;
    }
    @ApiOperation(value = "用户列表", notes = "获取用户列表")
    @RequestMapping("/list")
    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId((long) 1);
        user1.setName("sunshine1");
        user1.setAge(16);
        userList.add(user1);
        User user2 = new User();
        user2.setId((long) 2);
        user2.setName("sunshine2");
        user2.setAge(18);
        userList.add(user2);
        return  userList;
    }
    @ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @PostMapping("/add")
    public Map<String,Object> addUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUserById(@PathVariable("id") Long id){
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @PutMapping("/{id}")
    public Map<String, Object> updateUserById(@PathVariable("id") Long id){
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
}
