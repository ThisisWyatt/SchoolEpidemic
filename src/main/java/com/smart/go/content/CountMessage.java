package com.smart.go.content;

import java.util.Date;

/**
 * Description
 * Author cloudr 整合前端查询统计人数条件的工具类
 * Date 2020/5/3 22:32
 * Version 1.0
 **/
public class CountMessage {
    private String startTime;
    private String endTime;
    private String location;

    public CountMessage() {
    }

    public CountMessage(String startTime, String endTime, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
