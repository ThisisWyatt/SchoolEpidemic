package com.smart.go.domain;

import javax.persistence.*;

/**
 * Description AP实体
 * Author Cloudr
 * Date 2020/4/23 13:50
 **/
@Entity
@Table(name = "ap_info")
public class Ap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "AP_NAME") //AP名字
    private String apName;
    @Column(name = "IP_ADDRESS") //IP地址
    private String ipAddress;
    @Column(name = "MAC_ADDRESS") //MAC地址
    private String macAddress;
    @Column(name = "CAMPUS") // 校区
    private String campus;
    @Column(name = "BUILDING_NAME") //楼宇名称
    private String buildingName;
    @Column(name = "FLOOR_ID") //房间号
    private String floorId;
    @Column(name = "ROOM_ID") //房间号
    private String roomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
