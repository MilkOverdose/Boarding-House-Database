package com.example.boardinghouse;

import java.time.LocalDate;

public class Payment {
    private int paymentID;
    private int tenantID;
    private double amount;
    private LocalDate paymentDate;
    private String status; // "Paid", "Pending", "Overdue"

    public Payment(int paymentID, int tenantID, double amount, LocalDate paymentDate, String status) {
        this.paymentID = paymentID;
        this.tenantID = tenantID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getPaymentID() { return paymentID; }
    public int getTenantID() { return tenantID; }
    public double getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getStatus() { return status; }

    public void setAmount(double amount) { this.amount = amount; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public void setStatus(String status) { this.status = status; }
}