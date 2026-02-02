package com.arvin.store.util;

import java.util.regex.Pattern;

public class Validator {
    // Regex for basic username (alphanumeric, 3-20 chars)
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9]{3,20}$";

    // Regex for price (positive decimal)
    private static final String PRICE_REGEX = "^\\d+(\\.\\d{1,2})?$";

    // Regex for quantity (positive integer)
    private static final String QUANTITY_REGEX = "^[1-9]\\d*$";

    public static void validateUsername(String username) throws InvalidInputException {
        if (username == null || !Pattern.matches(USERNAME_REGEX, username)) {
            throw new InvalidInputException("Invalid Username: Must be 3-20 alphanumeric characters.");
        }
    }

    public static void validatePrice(double price) throws InvalidInputException {
        if (price < 0) {
            throw new InvalidInputException("Price cannot be negative.");
        }
    }

    public static void validateString(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static boolean isPositiveInt(String str) {
        return Pattern.matches(QUANTITY_REGEX, str);
    }

    public static boolean isPositiveDouble(String str) {
        return Pattern.matches(PRICE_REGEX, str);
    }
}
