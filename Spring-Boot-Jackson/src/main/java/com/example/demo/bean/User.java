package com.example.demo.bean;

import com.example.demo.config.UserSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"age"})
//@JsonSerialize(using = UserSerializer.class)
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;
    public interface UserNameView{}
    public interface AllUserFieldView extends UserNameView{}
    @JsonView(UserNameView.class)
    private String userName;
    @JsonView(AllUserFieldView.class)
    private int age;
    @JsonIgnore
    private String password;
    @JsonProperty("btn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
