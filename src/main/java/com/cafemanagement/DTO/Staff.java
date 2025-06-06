package com.cafemanagement.DTO;

import com.cafemanagement.utils.Day;

public class Staff {
    private String staffID;
    private String name;
    private boolean gender; // khi đọc dữ liệu từ database sẽ so sánh rồi đổi gender thành kiểu String
    private Day dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private double salary;
    private Day dateOfEntry;
    private boolean deleted; // khi đọc dữ liệu từ database sẽ so sánh rồi đổi thành kiểu boolean

    public Staff() {
    }

    public Staff(String staffID, String name, boolean gender, Day dateOfBirth, String address, String phone, String email, double salary, Day dateOfEntry, boolean deleted) {
        this.staffID = staffID;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.dateOfEntry = dateOfEntry;
        this.deleted = deleted;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Day getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Day dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Day getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Day dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        String gender1 = gender ? "Nam" : "Nữ";
        return staffID + " | " +
            name + " | " +
            gender1 + " | " +
            dateOfBirth + " | " +
            address + " | " +
            phone + " | " +
            email + " | " +
            salary + " | " +
            dateOfEntry;
    }
}
