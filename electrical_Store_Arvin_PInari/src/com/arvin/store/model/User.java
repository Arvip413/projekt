package com.arvin.store.model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String username;
    protected String password; // In a real app, this should be hashed

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // Abstract methods ensuring polymorphism
    public abstract String getRoleName();

    public abstract boolean canManageUsers();

    public abstract boolean canManageInventory();

    public abstract boolean canProcessSales();

    @Override
    public String toString() {
        return getRoleName() + ": " + username;
    }
}
