package com.gymapp.main.trackers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gymapp.main.activities.AbstractActivity;

public final class ActivityTracker implements Application.ActivityLifecycleCallbacks {

    private AbstractActivity currentActivity;

    private static ActivityTracker instance;

    public static ActivityTracker getInstance() {
        if(instance == null) instance = new ActivityTracker();
        return instance;
    }

    public void init(@NonNull Application application){
        application.registerActivityLifecycleCallbacks(this);
    }

    public AbstractActivity getCurrentActivity() {
        return currentActivity;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = (AbstractActivity) activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = (AbstractActivity) activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        if(currentActivity == activity) currentActivity = null;
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
