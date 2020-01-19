package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id).orElse(null);
    }

    @PostMapping
    public User saveUser(User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id,User user){
        userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
    }

    @GetMapping("/age/{from}/{to}")
    public List<User> findByAgeBetween(@PathVariable Integer from,@PathVariable Integer to){
        return userService.findByAgeBetween(from,to);
    }

    @GetMapping("/name/{name}")
    public List<User> getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @GetMapping("/description/{description}")
    public List<User> getByDescriptionIsLike(@PathVariable String description){
        return userService.getByDescriptionIsLike(description);
    }

    @GetMapping("/all/{from}/{to}/{name}/{description}")
    public List<User> getByNameEqualsAndAgeBetweenAndDescriptionIsLike(@PathVariable Integer from,@PathVariable Integer to,@PathVariable String name,@PathVariable String description){
        return userService.getByNameEqualsAndAgeBetweenAndDescriptionIsLike(from,to,name,description);
    }

    @GetMapping("/condition")
    public Page<User> getUserByCondition(int size, int page, User user) {
        return userService.getUserByCondition(size, page, user);
    }
}
