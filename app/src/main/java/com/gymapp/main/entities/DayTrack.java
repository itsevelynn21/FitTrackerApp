package com.gymapp.main.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public record DayTrack(
        int calories,
        int water,
        @Nullable String note,
        @NotNull GymStatus gymStatus,
        @NotNull LocalDate localDate
) {
    public DayTrack {
        if (calories < 0) throw new IllegalArgumentException("Calories cannot be negative");
        if (water < 0) throw new IllegalArgumentException("Water cannot be negative");
    }

    public DayTrack(@NotNull LocalDate localDate) {
        this(0, 0, null, GymStatus.NONE, localDate);
    }
}
