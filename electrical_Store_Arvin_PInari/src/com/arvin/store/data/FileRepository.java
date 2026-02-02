package com.arvin.store.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T extends Serializable> implements Repository<T> {
    private List<T> dataList;
    private String filePath;

    public FileRepository(String filePath) {
        this.filePath = filePath;
        this.dataList = new ArrayList<>();
        try {
            load();
        } catch (Exception e) {
            // File might not exist yet, that's okay
            System.out.println("Initializing new data file: " + filePath);
        }
    }

    @Override
    public void add(T item) {
        dataList.add(item);
        saveSafely();
    }

    @Override
    public void update(int index, T item) {
        if (index >= 0 && index < dataList.size()) {
            dataList.set(index, item);
            saveSafely();
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < dataList.size()) {
            dataList.remove(index);
            saveSafely();
        }
    }

    @Override
    public List<T> getAll() {
        return dataList;
    }

    @Override
    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dataList);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void load() throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists())
            return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                dataList = (List<T>) obj;
            }
        }
    }

    private void saveSafely() {
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace(); // In real app, log this
        }
    }
}
