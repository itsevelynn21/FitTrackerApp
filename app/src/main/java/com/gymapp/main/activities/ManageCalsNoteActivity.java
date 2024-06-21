package com.gymapp.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.gymapp.main.R;
import com.gymapp.main.app.GymApplication;
import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.viewmodel.ManageCalsViewModel;

public class ManageCalsNoteActivity extends AbstractActivity{

    private ManageCalsViewModel manageCalsViewModel;

    private Button addWater;
    private Button addCals;
    private Button goBack;
    private Button setNote;
    private EditText addWaterText;
    private EditText addCalsText;
    private EditText setNoteText;
    private TextView calsDisplay;
    private TextView waterDisplay;
    private GymApplication gymApplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        gymApplication = (GymApplication) getApplication();
        manageCalsViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ManageCalsViewModel.class);

        initializeUIViews();
        updateUI();
        setupButtonListeners();
    }
    private void initializeUIViews(){
        addWater = findViewById(R.id.add_water_button);
        addCals = findViewById(R.id.edit_calories_button);
        setNote = findViewById(R.id.set_note_button);
        goBack = findViewById(R.id.go_back);
        addWaterText = findViewById(R.id.edit_number_water);
        addCalsText = findViewById(R.id.edit_number_calories);
        setNoteText = findViewById(R.id.note_text);
        calsDisplay = findViewById(R.id.calories_counter);
        waterDisplay = findViewById(R.id.water_counter);
    }

    private void updateUI(){
       DayTrack dayTrack = manageCalsViewModel.getDayTrack();
       calsDisplay.setText(dayTrack.calories() + " kcal");
       waterDisplay.setText(dayTrack.water() + " mL");
       setNoteText.setText(dayTrack.note() != null ? dayTrack.note() : "None");
    }

    private void setupButtonListeners(){

        //GO BACK

        goBack.setOnClickListener((listener) -> {
            Intent intent = new Intent(ManageCalsNoteActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //ADD CALS

        addCals.setOnClickListener((listener) -> {
            int amount = Integer.parseInt(addCalsText.getText().toString());
            gymApplication.getDayTrackFacade().todayTrackAddCals(amount);
            manageCalsViewModel.reloadDayTrack();
            updateUI();
        });

        //ADD WATER

        addWater.setOnClickListener((listener) -> {
            int amount = Integer.parseInt(addWaterText.getText().toString());
            gymApplication.getDayTrackFacade().todayTrackAddWater(amount);
            manageCalsViewModel.reloadDayTrack();
            updateUI();
        });


        //SET NOTE

        setNote.setOnClickListener((listener) -> {
            String note = setNoteText.getText().toString();
            gymApplication.getDayTrackFacade().todayTrackWithNote(note);
        });

    }
}
