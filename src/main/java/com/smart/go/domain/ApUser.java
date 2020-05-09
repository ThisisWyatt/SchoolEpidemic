package com.smart.go.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description 用户信息实体
 * Author Cloudr
 * Date 2020/4/23 14:00
 * Instr 用户mac地址和用户ID共同组成主键
 **/
@Entity
@Table(name = "user_info")
@IdClass(ApUserMultiKeys.class)
public class ApUser implements Serializable {
    @Id
    @Column(name = "MAC_ADDRESS", length = 190)   //用户mac地址
    private String macAddress;
    @Id
    @Column(name = "USER_ID", length = 190)       // 用户ID
    private String userId;
    @Column(name = "USER_NAME")     //用户姓名
    private String userName;
    @Column(name = "IPV4_ADDRESS")  //用户ipv4地址
    private String ipv4Address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpv4Address() {
        return ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
