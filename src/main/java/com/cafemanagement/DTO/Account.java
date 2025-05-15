package com.cafemanagement.DTO;

public class Account {
    private String accountID;
    private String username;
    private String password;
    private String staffID;
    private boolean deleted; // khi đọc dữ liệu từ database sẽ so sánh rồi đổi thành kiểu boolean

    public Account() {
    }

    public Account(String accountID, String username, String password,  String staffID, boolean deleted) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.staffID = staffID;
        this.deleted = deleted;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return accountID + " | " +
            username + " | " +
            password + " | " +
            staffID;
    }
}
