package com.gymapp.main.app;

import android.app.Application;

import com.gymapp.main.database.DayTrackDAO;
import com.gymapp.main.database.DayTrackHelper;
import com.gymapp.main.database.GenDAO;
import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.handlers.DayChangeHandler;
import com.gymapp.main.managers.DayTrackFacade;
import com.gymapp.main.managers.DayTrackFacadeImpl;
import com.gymapp.main.repository.Repository;
import com.gymapp.main.repository.RepositoryImpl;

import java.time.LocalDate;

public final class GymApplication extends Application {


    private DayTrackFacade dayTrackFacade;

    //TODO set up current activity and LifecycleObserver

    @Override
    public void onCreate(){
        super.onCreate();
        DayTrackHelper dbHelper = new DayTrackHelper(getApplicationContext());
        Repository<LocalDate, DayTrack> repository = new RepositoryImpl<>();
        GenDAO<LocalDate, DayTrack> genDAO = new DayTrackDAO(dbHelper);
        dayTrackFacade = new DayTrackFacadeImpl(repository,genDAO);
        DayChangeHandler.getInstance().start();
        DayChangeHandler.getInstance().addListener(() -> {});
    }

    public DayTrackFacade getDayTrackFacade(){
        return dayTrackFacade;
    }


}
