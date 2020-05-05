package com.smart.go.util;

/**
 * Description
 * Author cloudr 保存提交轨迹追踪表单信息
 * Date 2020/5/6 0:48
 * Version 1.0
 **/
public class TrackFromMessage {
    private String Id;
    private String startTime;
    private String endTime;

    public TrackFromMessage() {
    }

    public TrackFromMessage(String id, String startTime, String endTime) {
        Id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
}
