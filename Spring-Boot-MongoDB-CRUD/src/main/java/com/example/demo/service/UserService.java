package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private MongoTemplate template;
    @Autowired
    private UserDao userDao;
    //修改和存储都是使用save方法
    public User saveUser(User user){
        user.setId(null);
        return userDao.save(user);
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public Optional<User> getUserById(String id){
        return userDao.findById(id);
    }

    public void updateUser(String id,User user){
        userDao.findById(id)
                .ifPresent(
                    u -> {
                        if(!StringUtils.isEmpty(user.getName())) {
                            u.setName(user.getName());
                        }
                        if(!StringUtils.isEmpty(user.getAge())) {
                            u.setAge(user.getAge());
                        }
                        if(!StringUtils.isEmpty(user.getDescription())) {
                            u.setDescription(user.getDescription());
                        }
                        userDao.save(u);
                    }
                );
    }

    public void deleteUserById(@PathVariable String id){
        userDao.deleteById(id);
    }

    public List<User> findByAgeBetween(Integer from,Integer to){
        return userDao.findByAgeBetween(from,to);
    }

    public List<User> getUserByName(String name){
        return userDao.findByNameEquals(name);
    }

    public List<User> getByDescriptionIsLike(String description){
        return userDao.findByDescriptionIsLike(description);
    }

    public List<User> getByNameEqualsAndAgeBetweenAndDescriptionIsLike(Integer from, Integer to, String name, String description){
        return userDao.findByNameEqualsAndAgeBetweenAndDescriptionIsLike(from, to, name, description);
    }

    public Page<User> getUserByCondition(int size,int page,User user){
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(user.getName())){
            criteria.and("name").is(user.getName());
        }
        if (!StringUtils.isEmpty(user.getDescription())) {
            criteria.and("description").regex(user.getDescription());
        }
        query.addCriteria(criteria);
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        Pageable pageable = PageRequest.of(size,page,sort);
        List<User> users = template.find(query.with(pageable), User.class);
        return PageableExecutionUtils.getPage(users,pageable,() -> template.count(query,User.class));
    }
}
