package com.example.boardinghouse;

public class Tenant {
    private String name;
    private int age;
    private int tenantID;
    private int roomNum;
    private String contact;

    public Tenant(String name, int age, int tenantID, int roomNum, String contact) {
        this.name = name;
        this.age = age;
        this.tenantID = tenantID;
        this.roomNum = roomNum;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTenantID() {
        return tenantID;
    }

    public void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        roomNum = roomNum;
    }

    public String getContact() {
        return contact;
    }
}
