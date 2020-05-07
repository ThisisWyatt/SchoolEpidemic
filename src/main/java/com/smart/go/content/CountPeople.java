package com.smart.go.content;

/**
 * Description
 * Author cloudr 统计人数时返回相关字段
 * Date 2020/5/7 22:16
 * Version 1.0
 **/
public class CountPeople {
    private String location;
    private int num;

    public CountPeople() {
    }

    public CountPeople(String location, int num) {
        this.location = location;
        this.num = num;
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

    @Override
    public String toString() {
        return "CountPeople{" +
                "location='" + location + '\'' +
                ", num=" + num +
                '}';
    }
}
