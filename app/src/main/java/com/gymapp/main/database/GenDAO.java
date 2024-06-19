package com.gymapp.main.database;

import java.util.Map;

public interface GenDAO<T, V> {

    void save(V v);
    void remove(V v);
    Map<T,V> getAll();
}
