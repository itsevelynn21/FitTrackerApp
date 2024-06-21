package com.gymapp.main.managers;

import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.entities.GymStatus;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public interface DayTrackFacade {


    void todayTrackAddCals(int calories);
    void todayTrackAddWater(int water);
    void todayTrackWithNote(@Nullable  String note);
    void todayTrackWithStatus(@NotNull GymStatus gymStatus);

    DayTrack getDayTrack(LocalDate localDate);
    DayTrack getTodayTrack();

}
