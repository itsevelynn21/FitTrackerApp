package com.gymapp.main.repository;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class RepositoryImpl<T, V> implements Repository<T, V> {

    private final HashMap<T, V> repoMap = new HashMap<>();


    @Override
    public V getItem(@NotNull T t) {
        return repoMap.get(t);
    }

    @Override
    public void saveItem(@NotNull T t, @NotNull V v) {
            repoMap.put(t,v);
    }

    @Override
    public void removeItem(@NotNull T t) {
        repoMap.remove(t);
    }

    @Override
    public void loadItems(@NotNull Map<T, V> map) {
        repoMap.putAll(map);
    }
}
