package com.arvin.store.controller;

import com.arvin.store.data.StoreData;
import com.arvin.store.model.Item;
import com.arvin.store.util.InvalidInputException;
import com.arvin.store.util.Validator;
import java.util.stream.Collectors;
import java.util.List;

public class InventoryController {

    public void addItem(String name, String category, String supplier, String date, String pPriceStr, String sPriceStr,
            String qtyStr) throws InvalidInputException {
        Validator.validateString(name, "Name");

        if (!Validator.isPositiveDouble(pPriceStr))
            throw new InvalidInputException("Invalid Purchase Price");
        if (!Validator.isPositiveDouble(sPriceStr))
            throw new InvalidInputException("Invalid Selling Price");
        if (!Validator.isPositiveInt(qtyStr))
            throw new InvalidInputException("Invalid Quantity");

        double pPrice = Double.parseDouble(pPriceStr);
        double sPrice = Double.parseDouble(sPriceStr);
        int qty = Integer.parseInt(qtyStr);

        Validator.validatePrice(pPrice);
        Validator.validatePrice(sPrice);

        Item newItem = new Item(name, category, supplier, date, pPrice, sPrice, qty);
        StoreData.getInstance().getItemRepository().add(newItem);
    }

    public void deleteItem(int index) {
        StoreData.getInstance().getItemRepository().delete(index);
    }

    public List<Item> getAllItems() {
        return StoreData.getInstance().getItemRepository().getAll();
    }

    public List<Item> searchItems(String query) {
        String lowerQuery = query.toLowerCase();
        return getAllItems().stream()
                .filter(i -> i.getName().toLowerCase().contains(lowerQuery)
                        || i.getCategory().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }
}
