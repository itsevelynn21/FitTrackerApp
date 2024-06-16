package com.gymapp.main.entities;

import android.annotation.SuppressLint;

import com.gymapp.main.exceptions.IllegalDayTrackModificationException;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public class DayTrack {

    private int calories;
    private int water;
    private String note;
    private final LocalDate localDate;

    public DayTrack(int calories, int water, @Nullable String note, LocalDate localDate){
        this.calories = calories;
        this.water = water;
        this.note = note;
        this.localDate = localDate;
    }

    public DayTrack(LocalDate localDate){
        this.localDate = localDate;
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

    public void update(int addCals, int addWater, @Nullable String setNote) throws IllegalDayTrackModificationException {
        if(isToday()){
            this.calories += addCals;
            this.water += addWater;
            this.note = setNote;
        } else {
            throw new IllegalDayTrackModificationException("DayTrack of past date cannot be modified");
        }
    }

    @SuppressLint("NewApi")
    public boolean isToday(){
        return LocalDate.now().equals(localDate);
    }
}
