package com.arvin.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;

    private String billNumber;
    private List<SoldItem> items;
    private double totalAmount;
    private String saleDate; // String for simplicity, formatted date
    private String cashierUsername;

    public Sale(String billNumber, String saleDate, String cashierUsername) {
        this.billNumber = billNumber;
        this.saleDate = saleDate;
        this.cashierUsername = cashierUsername;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addItem(SoldItem item) {
        items.add(item);
        totalAmount += item.getTotalPrice();
    }

    public String getBillNumber() {
        return billNumber;
    }

    public List<SoldItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public String getCashierUsername() {
        return cashierUsername;
    }

    @Override
    public String toString() {
        return "Bill: " + billNumber + " | Total: " + totalAmount + " | Date: " + saleDate;
    }
}
