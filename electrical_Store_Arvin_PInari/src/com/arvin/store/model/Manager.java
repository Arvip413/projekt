package com.arvin.store.model;

public class Manager extends User {
    private static final long serialVersionUID = 1L;

    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRoleName() {
        return "Manager";
    }

    @Override
    public boolean canManageUsers() {
        return false;
    }

    @Override
    public boolean canManageInventory() {
        return true;
    }

    @Override
    public boolean canProcessSales() {
        return true;
    } // Manager can also help sell if needed
}
