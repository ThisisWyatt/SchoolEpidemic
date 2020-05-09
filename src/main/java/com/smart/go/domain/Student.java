package com.smart.go.domain;

import javax.naming.Name;
import javax.persistence.*;

/**
 * Description 学生实体
 * Author Cloudr
 * Date 2020/4/23 13:29
 **/

@Entity
@Table(name = "student_info")
public class Student {
    @Id
    @Column(name = "STUDENT_NO", length = 190)   //学号
    private String studentNo;
    @Column(name = "NAME")                      //姓名
    private String name;
    @Column(name = "GENDER")                    //性别
    private String gender;
    @Column(name = "SCHOOL_YEAR")               //年级
    private String schoolYear;
    @Column(name = "COLLEGE_NAME")              //学院
    private String collegeName;
    @Column(name = "MAJOR_NAME")                //专业
    private String majorName;
    @Column(name = "CLASS_NAME")                //班级
    private String className;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
