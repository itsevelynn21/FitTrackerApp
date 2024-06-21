package com.gymapp.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gymapp.main.app.GymApplication;
import com.gymapp.main.entities.DayTrack;

public final class ManageCalsViewModel extends AndroidViewModel {

    private DayTrack dayTrack;
    private GymApplication gymApplication;

    public ManageCalsViewModel(@NonNull Application application) {
        super(application);
        gymApplication = (GymApplication) application;
        reloadDayTrack();
    }

    public DayTrack getDayTrack() {
        return dayTrack;
    }

    public GymApplication getGymApplication() {
        return gymApplication;
    }

    public void reloadDayTrack(){
        this.dayTrack = gymApplication.getDayTrackFacade().getTodayTrack();
    }
}
