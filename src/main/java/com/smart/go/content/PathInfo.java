package com.smart.go.content;


import java.util.Date;

/**
 * Description 记录被追踪者轨迹信息 （ 按照时间点 ）
 * Author cloudr
 * Date 2020/5/6 13:26
 * Version 1.0
 **/
public class PathInfo {
    private String peopleId;
    private String peopleName;
    private String department;
    private String location;
    private String lat;
    private String lng;
    private String time;

    public PathInfo() {
    }

    public PathInfo(String peopleId, String peopleName, String department, String location, String time, String lat, String lng) {
        this.peopleId = peopleId;
        this.peopleName = peopleName;
        this.department = department;
        this.location = location;
        this.time = time;
        this.lat = lat;
        this.lng = lng;
    }

    public PathInfo(String peopleId, String peopleName, String department) {
        this.peopleId = peopleId;
        this.peopleName = peopleName;
        this.department = department;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
