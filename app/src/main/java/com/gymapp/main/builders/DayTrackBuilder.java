package com.gymapp.main.builders;

import android.annotation.SuppressLint;

import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.entities.GymStatus;

import java.time.LocalDate;

public class DayTrackBuilder {

    private int calories;
    private int water;
    private String note;
    private GymStatus gymStatus = GymStatus.NONE;
    @SuppressLint("NewApi")
    private LocalDate localDate = LocalDate.now();

    public DayTrackBuilder withCalories(int calories){
        this.calories = calories;
        return this;
    }

    public DayTrackBuilder withWater(int water){
        this.water = water;
        return this;
    }

    public DayTrackBuilder withNote(String note){
        this.note = note;
        return this;
    }

    public DayTrackBuilder withGymStatus(GymStatus gymStatus){
        this.gymStatus = gymStatus;
        return this;
    }

    public DayTrackBuilder withLocalDate(LocalDate localDate){
        this.localDate = localDate;
        return this;
    }

    public DayTrack build(){
        return new DayTrack(calories,water,note,gymStatus,localDate);
    }
}
