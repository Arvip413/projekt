package com.arvin.store.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "transaction_log.txt";

    public static void logSale(String billDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write("SALE [" + LocalDateTime.now() + "]: " + billDetails);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logAction(String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write("ACTION [" + LocalDateTime.now() + "]: " + action);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
