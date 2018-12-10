package com.example.nouman.mosqueadmin;

public class ApproveIttekaafModel {
    private String name;
    private String phoneNo;
    private String status;

    public  ApproveIttekaafModel(){}
    public ApproveIttekaafModel(String name, String phoneNo, String status) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
