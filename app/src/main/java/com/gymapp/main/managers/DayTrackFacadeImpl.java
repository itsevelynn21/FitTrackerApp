package com.gymapp.main.managers;

import android.annotation.SuppressLint;

import com.gymapp.main.builders.DayTrackBuilder;
import com.gymapp.main.database.DayTrackDAO;
import com.gymapp.main.database.GenDAO;
import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.entities.GymStatus;
import com.gymapp.main.repository.Repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;


public final class DayTrackFacadeImpl implements DayTrackFacade {

    private final Repository<LocalDate, DayTrack> repository;
    private final GenDAO<LocalDate,DayTrack> dayTrackDAO;

    public DayTrackFacadeImpl(@NotNull Repository<LocalDate, DayTrack> repository,@NotNull GenDAO<LocalDate,DayTrack> dayTrackDAO){
        this.repository = repository;
        this.dayTrackDAO = dayTrackDAO;

        init();
    }

    private void init(){
        repository.loadItems(dayTrackDAO.getAll());
    }

    @Override
    public void todayTrackAddCals(int calories){
        DayTrack dayTrack = getTodayTrack();
        calories += dayTrack.getCalories();
        if(calories <= 0) return;
        DayTrack newTrack = new DayTrackBuilder().withCalories(calories).withWater(dayTrack.getWater()).withNote(dayTrack.getNote()).withGymStatus(dayTrack.getGymStatus()).withLocalDate(dayTrack.getLocalDate()).build();
        saveItem( newTrack);
    }

    @Override
    public void todayTrackAddWater(int water){
        DayTrack dayTrack = getTodayTrack();
        water += dayTrack.getWater();
        if(water<=0) return;
        DayTrack newTrack = new DayTrackBuilder().withCalories(dayTrack.getCalories()).withWater(water).withNote(dayTrack.getNote()).withGymStatus(dayTrack.getGymStatus()).withLocalDate(dayTrack.getLocalDate()).build();
        saveItem( newTrack);
    }

    @Override
    public void todayTrackWithNote(@Nullable String note){
        DayTrack dayTrack = getTodayTrack();
        DayTrack newTrack = new DayTrackBuilder().withCalories(dayTrack.getCalories()).withWater(dayTrack.getWater()).withNote(note).withGymStatus(dayTrack.getGymStatus()).withLocalDate(dayTrack.getLocalDate()).build();
        saveItem( newTrack);
    }

    @Override
    public void todayTrackWithStatus(@NotNull GymStatus gymStatus){
        DayTrack dayTrack = getTodayTrack();
        DayTrack newTrack = new DayTrackBuilder().withCalories(dayTrack.getCalories()).withWater(dayTrack.getWater()).withNote(dayTrack.getNote()).withGymStatus(gymStatus).withLocalDate(dayTrack.getLocalDate()).build();
        saveItem(newTrack);
    }


    @SuppressLint("NewApi")
    private DayTrack getTodayTrack(){
        return repository.getItem(LocalDate.now());
    }

    @Override
    public DayTrack getDayTrack(LocalDate localDate) {
        return repository.getItem(localDate);
    }

    private void saveItem(DayTrack dayTrack){
        repository.saveItem(dayTrack.getLocalDate(), dayTrack);
        dayTrackDAO.save(dayTrack);

    }
}
