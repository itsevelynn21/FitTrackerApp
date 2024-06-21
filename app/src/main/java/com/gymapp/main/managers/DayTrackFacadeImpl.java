package com.gymapp.main.managers;

import android.annotation.SuppressLint;

import com.gymapp.main.builders.DayTrackBuilder;
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
        calories += dayTrack.calories();
        if(calories <= 0) return;
        DayTrack newTrack = new DayTrackBuilder().withCalories(calories).withWater(dayTrack.water()).withNote(dayTrack.note()).withGymStatus(dayTrack.gymStatus()).withLocalDate(dayTrack.localDate()).build();
        saveItem( newTrack);
    }

    @Override
    public void todayTrackAddWater(int water){
        DayTrack dayTrack = getTodayTrack();
        water += dayTrack.water();
        if(water<=0) return;
        DayTrack newTrack = new DayTrackBuilder().withCalories(dayTrack.calories()).withWater(water).withNote(dayTrack.note()).withGymStatus(dayTrack.gymStatus()).withLocalDate(dayTrack.localDate()).build();
        saveItem( newTrack);
    }

    @Override
    public void todayTrackWithNote(@Nullable String note){
        DayTrack dayTrack = getTodayTrack();
        DayTrack newTrack = new DayTrackBuilder().withCalories(dayTrack.calories()).withWater(dayTrack.water()).withNote(note).withGymStatus(dayTrack.gymStatus()).withLocalDate(dayTrack.localDate()).build();
        saveItem( newTrack);
    }

    @Override
    public void todayTrackWithStatus(@NotNull GymStatus gymStatus){
        DayTrack dayTrack = getTodayTrack();
        DayTrack newTrack = new DayTrackBuilder().withCalories(dayTrack.calories()).withWater(dayTrack.water()).withNote(dayTrack.note()).withGymStatus(gymStatus).withLocalDate(dayTrack.localDate()).build();
        saveItem(newTrack);
    }


    @SuppressLint("NewApi")
    public DayTrack getTodayTrack(){
        return getDayTrack(LocalDate.now());
    }

    @Override
    public DayTrack getDayTrack(LocalDate localDate) {
        DayTrack dayTrack = repository.getItem(localDate);
        if(dayTrack == null) return new DayTrack(localDate);
        else return repository.getItem(localDate);
    }



    private void saveItem(DayTrack dayTrack){
        repository.saveItem(dayTrack.localDate(), dayTrack);
        dayTrackDAO.save(dayTrack);

    }
}
