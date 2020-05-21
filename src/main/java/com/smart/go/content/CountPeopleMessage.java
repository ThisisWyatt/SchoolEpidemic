package com.smart.go.content;

/**
 * Description
 * Author cloudr 统计人数时返回相关字段
 * Date 2020/5/7 22:16
 * Version 1.0
 **/
public class CountPeopleMessage {
    private String location;
    private int num;
    private String lat;
    private String lng;

    public CountPeopleMessage() {
    }

    public CountPeopleMessage(String location, int num) {
        this.location = location;
        this.num = num;
    }

    public CountPeopleMessage(String location, int num, String lat, String lng) {
        this.location = location;
        this.num = num;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    @Override
    public String toString() {
        return "CountPeople{" +
                "location='" + location + '\'' +
                ", num=" + num +
                '}';
    }
}
