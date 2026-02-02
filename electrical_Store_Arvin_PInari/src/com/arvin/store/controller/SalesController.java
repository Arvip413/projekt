package com.arvin.store.controller;

import com.arvin.store.data.Logger;
import com.arvin.store.data.StoreData;
import com.arvin.store.model.Item;
import com.arvin.store.model.Sale;
import com.arvin.store.model.SoldItem;
import com.arvin.store.model.User;
import com.arvin.store.util.InvalidInputException;

import java.time.LocalDateTime;
import java.util.UUID;

public class SalesController {

    private Sale currentSale;
    private User cashier; // The user processing the sale

    public SalesController(User cashier) {
        this.cashier = cashier;
        startNewSale();
    }

    public void startNewSale() {
        String billId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        currentSale = new Sale(billId, LocalDateTime.now().toString(), cashier.getUsername());
    }

    public void addToCart(Item item, int quantity) throws InvalidInputException {
        if (quantity > item.getQuantity()) {
            throw new InvalidInputException("Not enough stock. Available: " + item.getQuantity());
        }

        if (quantity <= 0) {
            throw new InvalidInputException("Quantity must be greater than 0.");
        }

        SoldItem soldItem = new SoldItem(item.getName(), item.getSellingPrice(), quantity);
        currentSale.addItem(soldItem);

        // Update stock temporarily (or permanently if we save now. Let's save stock
        // update on checkout)
    }

    public void checkout() {
        if (currentSale.getItems().isEmpty())
            return;

        // 1. Save Sale
        StoreData.getInstance().getSaleRepository().add(currentSale);

        // 2. Update Inventory
        for (SoldItem si : currentSale.getItems()) {
            // Find item by name (Simplified logical link)
            for (Item stockItem : StoreData.getInstance().getItemRepository().getAll()) {
                if (stockItem.getName().equals(si.getItemName())) {
                    stockItem.reduceQuantity(si.getQuantity());
                    break;
                }
            }
        }
        // Force save of inventory
        try {
            StoreData.getInstance().getItemRepository().save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Log
        Logger.logSale(currentSale.toString());

        startNewSale(); // Reset
    }

    public Sale getCurrentSale() {
        return currentSale;
    }

    public double getTotal() {
        return currentSale.getTotalAmount();
    }
}
