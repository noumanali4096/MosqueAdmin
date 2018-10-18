package com.example.nouman.mosqueadmin;

public class MosqueAdmin {

    String phone;
    String password;


    public MosqueAdmin(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
    public MosqueAdmin(){

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
