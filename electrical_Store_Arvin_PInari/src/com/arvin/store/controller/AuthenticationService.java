package com.arvin.store.controller;

import com.arvin.store.data.StoreData;
import com.arvin.store.model.User;
import com.arvin.store.util.AuthException;
import com.arvin.store.util.InvalidInputException;
import com.arvin.store.util.Validator;

public class AuthenticationService {

    public User login(String username, String password) throws AuthException, InvalidInputException {
        Validator.validateString(username, "Username");
        Validator.validateString(password, "Password");

        for (User user : StoreData.getInstance().getUserRepository().getAll()) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }
        throw new AuthException("Invalid username or password.");
    }
}
