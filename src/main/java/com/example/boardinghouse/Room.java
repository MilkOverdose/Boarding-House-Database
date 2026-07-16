package com.example.boardinghouse;

public class Room {
    private int roomID;
    private String roomNumber;
    private int capacity;
    private double price;
    private String status; // "Available", "Occupied", "Maintenance"

    public Room(int roomID, String roomNumber, int capacity, double price, String status) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.price = price;
        this.status = status;
    }

    public int getRoomID() { return roomID; }
    public String getRoomNumber() { return roomNumber; }
    public int getCapacity() { return capacity; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
}