package com.smart.go.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description
 * Author cloudr
 * Date 2020/6/18 0:17
 * Version 1.0
 **/
@Entity
@Table(name = "user_info")
public class User {
    @Id
    @Column(name = "user_id", length = 50)      //用户ID 学生学号或老师工号
    private String userId;
    @Column(name = "name")                       //姓名
    private String name;
    @Column(name = "department")                 //学生 班级或 老师部门
    private String department;

    public User(String userId, String name, String department) {
        this.userId = userId;
        this.name = name;
        this.department = department;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
