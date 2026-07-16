package com.example.boardinghouse;

public class Caretaker {
    private int caretakerID;
    private String name;
    private String contact;
    private String assignedArea;

    public Caretaker(int caretakerID, String name, String contact, String assignedArea) {
        this.caretakerID = caretakerID;
        this.name = name;
        this.contact = contact;
        this.assignedArea = assignedArea;
    }

    public int getCaretakerID() { return caretakerID; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getAssignedArea() { return assignedArea; }

    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    public void setAssignedArea(String assignedArea) { this.assignedArea = assignedArea; }
}