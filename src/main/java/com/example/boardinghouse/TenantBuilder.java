package com.example.boardinghouse;

public class TenantBuilder {
    private String name;
    private int age;
    private int tenantID;
    private int roomNum;
    private String contact;

    public TenantBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TenantBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public TenantBuilder setTenantID(int tenantID) {
        this.tenantID = tenantID;
        return this;
    }

    public TenantBuilder setRoomNum(int roomNum) {
        this.roomNum = roomNum;
        return this;
    }

    public TenantBuilder setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public Tenant build() {
        return new Tenant(name, age, tenantID, roomNum, contact);
    }
}