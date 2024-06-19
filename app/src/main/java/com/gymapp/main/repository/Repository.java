package com.gymapp.main.repository;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Repository<T, V> {

    V getItem(@NotNull T t);

    void saveItem(@NotNull T t, @NotNull V v);

    void removeItem(@NotNull T t);

    void loadItems(@NotNull Map<T,V> map);
}
