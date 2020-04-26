package com.smart.go.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description  单条日志提取后的数据实体
 * Author Cloudr
 * Date 2020/4/22 14:09
 * Instr 日志记录时间和用户mac地址共同组成主键
 **/
@Entity
@Table(name = "aclog_result")
public class SingleLog {

    @Id
    private Date recordTime;
    @Id
    private String userMac;
    private String apName;
    private String apNameFrom;
    private String apNameTo;
    private int type;

    public SingleLog() {
    }

    public SingleLog( Date recordTime, String userMac, String apName, String apNameFrom, String apNameTo, int type) {
        this.recordTime = recordTime;
        this.userMac = userMac;
        this.apName = apName;
        this.apNameFrom = apNameFrom;
        this.apNameTo = apNameTo;
        this.type = type;
    }



    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public String getApNameFrom() {
        return apNameFrom;
    }

    public void setApNameFrom(String apNameFrom) {
        this.apNameFrom = apNameFrom;
    }

    public String getApNameTo() {
        return apNameTo;
    }

    public void setApNameTo(String apNameTo) {
        this.apNameTo = apNameTo;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
