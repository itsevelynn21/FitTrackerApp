package com.gymapp.main.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public final class DayTrackHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "gymtrack.db";

    private static final String CREATE_TABLE =  "CREATE TABLE " + TrackerContract.DayTrackEntry.TABLE_NAME + " (" +
            TrackerContract.DayTrackEntry._ID + " INTEGER PRIMARY KEY," +
            TrackerContract.DayTrackEntry.COLUMN_NAME_DATE + " DATE NOT NULL UNIQUE," +
            TrackerContract.DayTrackEntry.COLUMN_NAME_CALORIES + " INTEGER NOT NULL," +
            TrackerContract.DayTrackEntry.COLUMN_NAME_WATER + " INTEGER NOT NULL," +
            TrackerContract.DayTrackEntry.COLUMN_NAME_NOTE + " TEXT," +
            TrackerContract.DayTrackEntry.COLUMN_NAME_GYM_STATUS + " TEXT NOT NULL" +
            ")";


    public DayTrackHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
