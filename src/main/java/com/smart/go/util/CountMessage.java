package com.smart.go.util;

/**
 * Description
 * Author cloudr 整合前端查询统计人数条件的工具类
 * Date 2020/5/3 22:32
 * Version 1.0
 **/
public class CountMessage {
    private String StartTime;
    private String endTime;
    private String Location;

    public CountMessage(String startTime, String endTime, String location) {
        StartTime = startTime;
        this.endTime = endTime;
        Location = location;
    }

    public CountMessage() {
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
