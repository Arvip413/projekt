package com.arvin.store.data;

import com.arvin.store.model.Item;
import com.arvin.store.model.User;
import com.arvin.store.model.Manager;
import com.arvin.store.model.Cashier;
import com.arvin.store.model.Administrator;
import com.arvin.store.model.Sale;

public class StoreData {
    private static StoreData instance;

    private Repository<User> userRepository;
    private Repository<Item> itemRepository;
    private Repository<Sale> saleRepository;

    private StoreData() {
        userRepository = new FileRepository<>("users.dat");
        itemRepository = new FileRepository<>("items.dat");
        saleRepository = new FileRepository<>("sales.dat");

        // Seed Users if missing
        seedUser(new Administrator("admin", "admin123"));
        seedUser(new Manager("manager", "manager123"));
        seedUser(new Cashier("cashier", "cashier123"));
    }

    private void seedUser(User user) {
        boolean exists = userRepository.getAll().stream()
                .anyMatch(u -> u.getUsername().equals(user.getUsername()));
        if (!exists) {
            userRepository.add(user);
            System.out.println("Seeded user: " + user.getUsername());
        }
    }

    public static StoreData getInstance() {
        if (instance == null) {
            instance = new StoreData();
        }
        return instance;
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public Repository<Item> getItemRepository() {
        return itemRepository;
    }

    public Repository<Sale> getSaleRepository() {
        return saleRepository;
    }
}
