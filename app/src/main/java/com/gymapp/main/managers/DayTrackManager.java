package com.gymapp.main.managers;

import com.gymapp.main.entities.DayTrack;

import java.time.LocalDate;

public interface DayTrackManager {

    void updateTodayDaytrack(int calories, int water, String note);

    DayTrack getDayTrack(LocalDate localDate);

}
