package com.gymapp.main.entities;

import android.annotation.SuppressLint;

import com.gymapp.main.exceptions.IllegalDayTrackModificationException;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public class DayTrack {

    private int calories;
    private int water;
    private String note;
    private GymStatus gymStatus;
    private final LocalDate localDate;

    public DayTrack(int calories, int water, @Nullable String note, @NotNull GymStatus gymStatus,@NotNull LocalDate localDate){
        this.calories = calories;
        this.water = water;
        this.note = note;
        this.gymStatus = gymStatus;
        this.localDate = localDate;
    }

    public DayTrack(@NotNull LocalDate localDate){
        this.localDate = localDate; this.gymStatus = GymStatus.NONE;
    }


    public int getCalories() {
        return calories;
    }

    public int getWater() {
        return water;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public GymStatus getGymStatus() {
        return gymStatus;
    }



    @SuppressLint("NewApi")
    public boolean isToday(){
        return LocalDate.now().equals(localDate);
    }
}
