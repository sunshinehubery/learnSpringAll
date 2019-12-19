package com.example.demo;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(userService.getSequence("seq_user"));
        user.setUsername("sunshine");
        user.setPasswd("wwwwww1111113233");
        user.setCreateTime(new Date());
        user.setStatus("1");
        this.userService.save(user);
    }

    @Test
    public void selectByExample(){
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username like '%t%'");
        example.setOrderByClause("id desc");
        List<User> userList = userService.selectByExample(example);
        for (User user : userList) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void selectById(){
        User u = new User();
        u.setId(2L);
        u = this.userService.selectByKey(u);
        System.out.println(u.getUsername());
    }

    @Test
    public void deleteById(){
        User user = new User();
        user.setId(7L);
        this.userService.delete(user);
    }

    @Test
    public void selectByPage(){
        PageHelper.offsetPage(0,5);   //offset表示某一页的数据，limit表示该页数量
        List<User> userList = userService.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        List<User> list = pageInfo.getList();
        for (User u : list) {
            System.out.println(u.getUsername());
        }
    }
}
