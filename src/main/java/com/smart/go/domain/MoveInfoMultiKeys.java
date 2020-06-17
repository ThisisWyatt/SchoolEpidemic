package com.smart.go.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Description MoveInfo的复合主键类
 * Author Cloudr
 * Date 2020/4/26 23:50
 **/
public class MoveInfoMultiKeys implements Serializable {

    private Long orderId;
    private String peopleId;
    private Date recordTime;

    public MoveInfoMultiKeys() {
    }

    public MoveInfoMultiKeys(String peopleId, Date recordTime, Long orderId) {
        this.peopleId = peopleId;
        this.recordTime = recordTime;
        this.orderId = orderId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveInfoMultiKeys that = (MoveInfoMultiKeys) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(peopleId, that.peopleId) &&
                Objects.equals(recordTime, that.recordTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, peopleId, recordTime);
    }
}
