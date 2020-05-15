package com.smart.go.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description 人员移动信息实体
 * Author Cloudr
 * Date 2020/4/23 20:35
 * Instr 学号或者职工号和记录时间共同组成时间
 **/
@Entity
@Table(name = "move_info")
@IdClass(MoveInfoMultiKeys.class)
public class MoveInfo implements Serializable {
    @Id
    @Column(name = "people_id", length = 190)     //学号或职工号
    private String peopleId;
    @Id
    @Column(name = "record_time", length = 190)   //记录时间
    private Date recordTime;
    @Column(name = "name")          //姓名
    private String name;
    @Column(name = "department")    //学生班级或者职工部门
    private String department;
    @Column(name = "location")      //AP增加或删除的地点
    private String location;
    @Column(name = "location_from") //AP切换或漫游的起始点
    private String locationFrom;
    @Column(name = "location_to")   //AP切换或漫游的终点
    private String locationTo;
    @Column(name = "ap_type")       //AP变换类型
    private String apType;


    public MoveInfo() {
    }

    public MoveInfo(String peopleId, String name, String department, String location, String locationFrom, String locationTo, Date recordTime, String apType) {
        this.peopleId = peopleId;
        this.name = name;
        this.department = department;
        this.location = location;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.recordTime = recordTime;
        this.apType = apType;

    }


    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getApType() {
        return apType;
    }

    public void setApType(String apType) {
        this.apType = apType;
    }

    @Override
    public String toString() {
        return "MoveInfo{" +
                "peopleId='" + peopleId + '\'' +
                ", recordTime=" + recordTime +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                ", locationFrom='" + locationFrom + '\'' +
                ", locationTo='" + locationTo + '\'' +
                ", apType='" + apType + '\'' +
                '}';
    }
}
