package com.arvin.store.model;

import java.io.Serializable;

public class SoldItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemName;
    private double unitPrice;
    private int quantity;

    public SoldItem(String itemName, double unitPrice, int quantity) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d @ %.2f", itemName, quantity, unitPrice);
    }
}
