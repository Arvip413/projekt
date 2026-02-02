package com.arvin.store.model;

public class Administrator extends User {
    private static final long serialVersionUID = 1L;

    public Administrator(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRoleName() {
        return "Administrator";
    }

    @Override
    public boolean canManageUsers() {
        return true;
    }

    @Override
    public boolean canManageInventory() {
        return true;
    }

    @Override
    public boolean canProcessSales() {
        return true;
    }
}
