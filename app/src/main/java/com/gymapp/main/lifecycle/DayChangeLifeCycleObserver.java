package com.gymapp.main.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.gymapp.main.handlers.DayChangeHandler;

public final class DayChangeLifeCycleObserver implements LifecycleObserver {

    private final DayChangeHandler dayChangeHandler;

    public DayChangeLifeCycleObserver() {
        this.dayChangeHandler = DayChangeHandler.getInstance();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        dayChangeHandler.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        dayChangeHandler.stop();
    }
}
