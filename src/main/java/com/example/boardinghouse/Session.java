package com.example.boardinghouse;

import java.io.Serializable;

public class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userID;
    private String username;
    private String role; // "Owner" or "Caretaker"

    public Session(int userID, String username, String role) {
        this.userID = userID;
        this.username = username;
        this.role = role;
    }

    public int getUserID() { return userID; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}