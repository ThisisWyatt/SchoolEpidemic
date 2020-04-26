package com.smart.go.domain;

import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.naming.Name;
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
@IdClass(SingleLogMultiKeys.class)
public class SingleLog implements Serializable {

    @Id
    @Column(name = "record_time",length = 190)
    private Date recordTime;    //记录时间
    @Id
    @Column(name = "user_mac",length = 190)
    private String userMac;     //用户mac地址
    @Column(name = "ap_name")
    private String apName;      //AP增加或减少用户时的AP名字
    @Column(name = "ap_name_from")
    private String apNameFrom;  //AP漫游或切换时源AP名称
    @Column(name = "ap_name_to")
    private String apNameTo;    //AP漫游或切换时目的AP名称
    @Column(name = "type")
    private int type;           //日志记录的AP切换类型 1:切换 2:漫游 3:增加 4:减少

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
