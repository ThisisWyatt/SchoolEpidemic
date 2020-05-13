package com.smart.go.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultBean {

    private boolean success;
    private String message;
    private Object data;
    private List<?> dataList;

    /**
     * @param success
     * @param data
     * @param message
     */
    public ResultBean(boolean success, Object data, String message) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResultBean(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public ResultBean() {
        super();
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [success=" + success + ", message=" + message + "]";
    }


}
