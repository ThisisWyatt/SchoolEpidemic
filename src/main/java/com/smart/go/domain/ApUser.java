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
@Table(name = "ap_user_info")
@IdClass(ApUserMultiKeys.class)
public class ApUser implements Serializable {
    @Id
    @Column(name = "MAC_ADDRESS", length = 32)   //用户mac地址
    private String macAddress;
    @Id
    @Column(name = "USER_ID", length = 16)       // 用户ID
    private String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
