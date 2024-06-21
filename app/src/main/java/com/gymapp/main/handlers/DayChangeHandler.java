package com.gymapp.main.handlers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class DayChangeHandler {

    private static DayChangeHandler instance;
    private final Handler handler;
    private final Runnable runnable;
    private LocalDate currentDate;
    private final List<DayChangeListener> dayChangeListenerList = new ArrayList<>();

    private DayChangeHandler() {
        handler = new Handler(Looper.getMainLooper());
        currentDate = LocalDate.now();
        runnable = this::checkDay;
    }

    public static DayChangeHandler getInstance(){
        if(instance == null) instance = new DayChangeHandler();
        return instance;
    }

    public void start(){
        handler.post(runnable);
    }

    public void stop(){
        handler.removeCallbacks(runnable);
    }

    @SuppressLint("NewApi")
    private void checkDay(){
        if(!LocalDate.now().equals(currentDate)){
            currentDate = LocalDate.now();
            callAllListeners();
        }

        handler.postDelayed(runnable,60000);
    }

    private void callAllListeners(){
        dayChangeListenerList.forEach(DayChangeListener::onDayChange);
    }

    public void addListener(DayChangeListener dayChangeListener) {
        dayChangeListenerList.add(dayChangeListener);
    }
}
