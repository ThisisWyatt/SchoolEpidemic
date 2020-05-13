package com.smart.go.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Description MoveInfo的复合主键类
 * Author Cloudr
 * Date 2020/4/26 23:50
 **/
public class MoveInfoMultiKeys implements Serializable {

    private String peopleId;
    private Date recordTime;

    public MoveInfoMultiKeys() {
    }

    public MoveInfoMultiKeys(String peopleId, Date recordTime) {
        this.peopleId = peopleId;
        this.recordTime = recordTime;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveInfoMultiKeys that = (MoveInfoMultiKeys) o;
        return peopleId.equals(that.peopleId) &&
                recordTime.equals(that.recordTime);
    }

    @Override
    public int hashCode() {
        int code = 17;
        code = code * 31 + (peopleId != null ? peopleId.hashCode() : 0);
        code = code * 31 + (recordTime != null ? recordTime.hashCode() : 0);
        return code;
    }
}
