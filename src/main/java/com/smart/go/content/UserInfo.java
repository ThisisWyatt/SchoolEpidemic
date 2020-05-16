package com.smart.go.content;

/**
 * Description 返回根据Id查询的用户基本信息
 * Author cloudr
 * Date 2020/5/16 15:29
 * Version 1.0
 **/
public class UserInfo {
    private String id;
    private String name;
    private String department;

    public UserInfo() {
    }

    public UserInfo(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
