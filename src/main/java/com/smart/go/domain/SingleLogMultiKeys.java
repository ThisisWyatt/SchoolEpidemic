package com.smart.go.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/27 0:56
 **/
public class SingleLogMultiKeys implements Serializable {

    private Date recordTime;
    private String userMac;

    public SingleLogMultiKeys() {
    }

    public SingleLogMultiKeys(Date recordTime, String userMac) {
        this.recordTime = recordTime;
        this.userMac = userMac;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLogMultiKeys that = (SingleLogMultiKeys) o;
        return recordTime.equals(that.recordTime) &&
                userMac.equals(that.userMac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordTime, userMac);
    }
}
