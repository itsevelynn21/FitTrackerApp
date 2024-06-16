package com.gymapp.main.app;

import android.app.Application;

import com.gymapp.main.managers.DayTrackManager;

public class GymApplication extends Application {

    private final DayTrackManager dayTrackManager;

    public GymApplication(DayTrackManager dayTrackManager){
        this.dayTrackManager = dayTrackManager;
    }

}
