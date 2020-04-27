package com.smart.go.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/26 23:54
 **/
public class ApUserMultiKeys implements Serializable {

    private String macAddress;
    private String userId;

    public ApUserMultiKeys(String macAddress, String userId) {
        this.macAddress = macAddress;
        this.userId = userId;
    }

    public ApUserMultiKeys() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApUserMultiKeys that = (ApUserMultiKeys) o;
        return macAddress.equals(that.macAddress) &&
                userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(macAddress, userId);
    }

}
