package com.cafemanagement.DTO;

import com.cafemanagement.utils.Day;

public class Bill {
    private String billID;
    private String staffID;
    private Day dateOfPurchase;
    private double total;
    private double received;
    private double excess;

    private boolean deleted; // khi đọc dữ liệu từ database sẽ so sánh rồi đổi thành kiểu boolean

    public Bill() {
    }

    public Bill(String billID,  String staffID, Day dateOfPurchase, double total, double received, double excess, boolean deleted) {//String customerID,
        this.billID = billID;
        this.staffID = staffID;
        this.dateOfPurchase = dateOfPurchase;
        this.total = total;
        this.received = received;
        this.excess = excess;
        this.deleted = deleted;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public Day getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Day dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getReceived() {
        return received;
    }

    public void setReceived(double received) {
        this.received = received;
    }

    public double getExcess() {
        return excess;
    }

    public void setExcess(double excess) {
        this.excess = excess;
    }

    @Override
    public String toString() {
        return billID +  " | " +
            staffID + " | " +
            dateOfPurchase + " | " +
            total + " | " +
            received + " | " +
            excess;
    }
}
