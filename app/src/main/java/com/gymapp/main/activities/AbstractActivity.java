package com.gymapp.main.activities;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity {
    private boolean isResumedFromBg = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isResumedFromBg) {
            finish();
            startActivity(getIntent());
        }
        isResumedFromBg = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumedFromBg = true;
    }

}
