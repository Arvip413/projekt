package com.arvin.store.data;

import java.util.List;

public interface Repository<T> {
    void add(T item);

    void update(int index, T item);

    void delete(int index);

    List<T> getAll();

    void save() throws Exception;

    void load() throws Exception;
}
