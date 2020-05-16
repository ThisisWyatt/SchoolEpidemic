package com.smart.go.content;

/**
 * Description
 * Author cloudr 传递前端查询用户信息的类
 * Date 2020/5/16 16:36
 * Version 1.0
 **/
public class GetUserInfoMessage {
    private String id;

    public GetUserInfoMessage() {
    }

    public GetUserInfoMessage(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
