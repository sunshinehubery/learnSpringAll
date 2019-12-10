package com.example.demo.bean;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -339516038496531943L;
    private String sno;
    private String sname;
    private String ssex;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return sname;
    }

    public void setName(String name) {
        this.sname = name;
    }

    public String getSex() {
        return ssex;
    }

    public void setSex(String sex) {
        this.ssex = sex;
    }
}
