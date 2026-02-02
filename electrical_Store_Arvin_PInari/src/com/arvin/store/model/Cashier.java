package com.arvin.store.model;

public class Cashier extends User {
    private static final long serialVersionUID = 1L;

    public Cashier(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRoleName() {
        return "Cashier";
    }

    @Override
    public boolean canManageUsers() {
        return false;
    }

    @Override
    public boolean canManageInventory() {
        return false;
    } // Assuming Cashier only sells

    @Override
    public boolean canProcessSales() {
        return true;
    }
}
