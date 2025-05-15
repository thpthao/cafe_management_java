package com.cafemanagement.DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafemanagement.DTO.Bill;
import com.cafemanagement.utils.Day;

public class BillDAL extends Manager {
    public BillDAL() {
        super("bill",
            List.of("BILL_ID",
                "STAFF_ID",
                "DOPURCHASE",
                "TOTAL",
                "RECEIVED",
                "EXCESS",
                "DELETED")
        );
    }

    public List<Bill> convertToBills(List<List<String>> data) {
        return convert(data, row -> {
            try {
                return new Bill(
                    row.get(0), // billID
                    row.get(1), // staffID
                    Day.parseDay(row.get(2)), // dateOfPurchase
                    Double.parseDouble(row.get(3)), // total
                    Double.parseDouble(row.get(4)), // receive
                    Double.parseDouble(row.get(5)), // excess
                    Boolean.parseBoolean(row.get(6)) // deleted
                );
            } catch (Exception e) {
                System.out.println("Error occurred in BillDAL.convertToBills(): " + e.getMessage());
            }
            return new Bill();
        });
    }

    public int addBill(Bill bill) {
        try {
            return create(bill.getBillID(),
                bill.getStaffID(),
                bill.getDateOfPurchase(),
                bill.getTotal(),
                bill.getReceived(),
                bill.getExcess(),
                false
            ); // bill khi tạo mặc định total, deleted = 0
        } catch (SQLException e) {
            System.out.println("Error occurred in BillDAL.addBill(): " + e.getMessage());
        }
        return 0;
    }

    public int updateBill(Bill bill) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(bill.getBillID());
            updateValues.add(bill.getStaffID());
            updateValues.add(bill.getDateOfPurchase());
            updateValues.add(bill.getTotal());
            updateValues.add(bill.getReceived());
            updateValues.add(bill.getExcess());
            updateValues.add(bill.isDeleted());
            return update(updateValues, "BILL_ID = '" + bill.getBillID() + "'");
        } catch (SQLException e) {
            System.out.println("Error occurred in BillDAL.updateBill(): " + e.getMessage());
        }
        return 0;
    }

    public int deleteBill(String... conditions) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(true);
            return update(updateValues, conditions);
        } catch (SQLException e) {
            System.out.println("Error occurred in BillDAL.deleteBill(): " + e.getMessage());
        }
        return 0;
    }

    public List<Bill> searchBills(String... conditions) {
        try {
            return convertToBills(read(conditions));
        } catch (SQLException e) {
            System.out.println("Error occurred in BillDAL.searchBills(): " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Bill> searchBills(Day start, Day end) {
        try {
            String[] conditions = new String[]{
                "DOPURCHASE BETWEEN '" + start.toMySQLString() + "' AND '" + end.toMySQLString() + "'",
                "DELETED = 0"
            };
            return convertToBills(read(conditions));
        } catch (SQLException e) {
            System.out.println("Error occurred in BillDAL.searchBills(): " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public Bill getTheLastBill() {
        try {
            return convertToBills(executeQuery("""
                SELECT * FROM `bill`
                WHERE DELETED = 0
                ORDER BY DOPURCHASE DESC
                LIMIT 1;
                """)).get(0);
        } catch (SQLException e) {
            System.out.println("Error occurred in BillDAL.getTheLastBill(): " + e.getMessage());
        }
        return new Bill();
    }
}
