package com.smart.go.domain;

import javax.persistence.*;

/**
 * Description  教职工实体
 * Author Cloudr
 * Date 2020/4/23 13:44
 **/
@Entity
@Table(name = "teacher_info")
public class Teacher {

    @Id
    @Column(name = "EMPLOYEE_ID",length = 190)   //职工号
    private String employeeId;
    @Column(name = "NAME")                       //姓名
    private String name;
    @Column(name = "DEPARTMENT")                 //部门
    private String department;


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
