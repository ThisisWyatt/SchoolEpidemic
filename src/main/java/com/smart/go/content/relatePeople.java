package com.smart.go.content;

/**
 * Description
 * Author cloudr
 * Date 2020/5/6 16:16
 * Version 1.0
 **/
public class relatePeople {
    private String peopleId;
    private String name;
    private String department;

    public relatePeople() {
    }

    public relatePeople(String peopleId, String name, String department) {
        this.peopleId = peopleId;
        this.name = name;
        this.department = department;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
