package com.gymapp.main.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.gymapp.main.R;
import com.gymapp.main.app.GymApplication;
import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.entities.GymStatus;
import com.gymapp.main.viewmodel.MainActivityViewModel;

import java.util.concurrent.atomic.AtomicReference;

public final class MainActivity extends AbstractActivity {

    private MainActivityViewModel mainActivityViewModel;
    private GymApplication gymApplication;

    private TextView waterCounter;
    private TextView caloriesCounter;
    private Button changeStatus;
    private RelativeLayout statusLayout;
    private TextView statusView;
    private Button manageCals;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainActivityViewModel.class);
        gymApplication = (GymApplication) getApplication();

        initializeUIViews();
        updateUI();
        setupButtonListeners();




    }

    private void initializeUIViews(){
        waterCounter = findViewById(R.id.water_counter);
        caloriesCounter = findViewById(R.id.calories_counter);
        changeStatus = findViewById(R.id.change_status);
        statusLayout = findViewById(R.id.status);
        statusView = findViewById(R.id.status_view);
        manageCals= findViewById(R.id.manage_cals);
    }

    private void updateUI(){
        DayTrack dayTrack = mainActivityViewModel.getDayTrack();
        waterCounter.setText(String.valueOf(dayTrack.water()));
        caloriesCounter.setText(String.valueOf(dayTrack.calories()));

        switch (dayTrack.gymStatus()){
            case WENT -> {
                ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.teal_200));
                statusLayout.setBackgroundTintList(colorStateList);
                statusView.setText("WORKED OUT! ✅");
            }
            case NONE -> {
                statusLayout.setBackgroundTintList(null);
                statusView.setText("HAVEN'T WORKED OUT ❌");
            }
        }
    }

    private void setupButtonListeners(){
        AtomicReference<DayTrack> dayTrack = new AtomicReference<>(mainActivityViewModel.getDayTrack());


        //Change Status Button
        changeStatus.setOnClickListener((listener) -> {
            switch (dayTrack.get().gymStatus()){
                case NONE -> {
                    ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.teal_200));
                    statusLayout.setBackgroundTintList(colorStateList);
                    statusView.setText("WORKED OUT! ✅");
                    gymApplication.getDayTrackFacade().todayTrackWithStatus(GymStatus.WENT);
                    mainActivityViewModel.reloadDayTrack();
                }
                case WENT -> {
                    statusLayout.setBackgroundTintList(null);
                    statusView.setText("HAVEN'T WORKED OUT ❌");
                    gymApplication.getDayTrackFacade().todayTrackWithStatus(GymStatus.NONE);
                    mainActivityViewModel.reloadDayTrack();
                }
            }
            dayTrack.set(mainActivityViewModel.getDayTrack());
        });

        //MANAGE CALS,WATER,NOTE BUTTON

        manageCals.setOnClickListener((listener) -> {
            Intent intent = new Intent(MainActivity.this, ManageCalsNoteActivity.class);
            startActivity(intent);
        });
    }

}
