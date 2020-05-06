package com.smart.go.content;


import java.util.Date;

/**
 * Description 记录被追踪者轨迹信息
 * Author cloudr
 * Date 2020/5/6 13:26
 * Version 1.0
 **/
public class PathInfo {
    private String locationFrom;
    private String locationTo;
    private Date startTime;
    private Date endTime;

    public PathInfo() {
    }

    public PathInfo(String locationFrom, String locationTo, Date startTime, Date endTime) {
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "PathInfo{" +
                "locationFrom='" + locationFrom + '\'' +
                ", locationTo='" + locationTo + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
