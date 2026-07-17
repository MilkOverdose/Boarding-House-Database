package com.example.boardinghouse;

public class Owner {
    private int ownerID;
    private String username;
    private String password;
    private String contact;

    public Owner(int ownerID, String username, String password, String email, String contact) {
        this.ownerID = ownerID;
        this.username = username;
        this.password = password;
        this.contact = contact;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
