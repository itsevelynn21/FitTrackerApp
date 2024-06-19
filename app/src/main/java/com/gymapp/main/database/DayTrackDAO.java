package com.gymapp.main.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gymapp.main.builders.DayTrackBuilder;
import com.gymapp.main.entities.DayTrack;
import com.gymapp.main.entities.GymStatus;
import com.gymapp.main.utils.DateFormats;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class DayTrackDAO implements GenDAO<LocalDate, DayTrack> {

    private final SQLiteOpenHelper sqLiteOpenHelper;

    public DayTrackDAO(SQLiteOpenHelper sqLiteOpenHelper){
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public void save(DayTrack dayTrack) {
        ContentValues values = new ContentValues();
        values.put(TrackerContract.DayTrackEntry.COLUMN_NAME_CALORIES, dayTrack.calories());
        values.put(TrackerContract.DayTrackEntry.COLUMN_NAME_WATER, dayTrack.water());
        values.put(TrackerContract.DayTrackEntry.COLUMN_NAME_NOTE, dayTrack.note());
        values.put(TrackerContract.DayTrackEntry.COLUMN_NAME_GYM_STATUS, dayTrack.gymStatus().name());
        values.put(TrackerContract.DayTrackEntry.COLUMN_NAME_DATE, DateFormats.localDateString(dayTrack.localDate()));

        try(SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase()){
            db.insertWithOnConflict(TrackerContract.DayTrackEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e){
            Timber.e(e);
        }
    }

    @Override
    public void remove(DayTrack dayTrack) {

        String selection = "date = ?";
        String[] selectionArgs = {DateFormats.localDateString(dayTrack.localDate())};

        try(SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase()){
            db.delete(TrackerContract.DayTrackEntry.TABLE_NAME,selection,selectionArgs);
        }catch (Exception e){
            Timber.e(e);
        }


    }

    @Override
    public Map<LocalDate, DayTrack> getAll() {
        Map<LocalDate, DayTrack> map = new HashMap<>();

        String[] columns = {
                TrackerContract.DayTrackEntry._ID,
                TrackerContract.DayTrackEntry.COLUMN_NAME_DATE,
                TrackerContract.DayTrackEntry.COLUMN_NAME_CALORIES,
                TrackerContract.DayTrackEntry.COLUMN_NAME_WATER,
                TrackerContract.DayTrackEntry.COLUMN_NAME_GYM_STATUS,
                TrackerContract.DayTrackEntry.COLUMN_NAME_NOTE
        };

        try(SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    TrackerContract.DayTrackEntry.TABLE_NAME,
                    columns,
                    null, null, null, null, null
            )){
            while (cursor.moveToNext()) {
                String dateString = cursor.getString(cursor.getColumnIndexOrThrow(TrackerContract.DayTrackEntry.COLUMN_NAME_DATE));
                LocalDate date = DateFormats.localDateOf(dateString);
                String gymStatus = cursor.getString(cursor.getColumnIndexOrThrow(TrackerContract.DayTrackEntry.COLUMN_NAME_GYM_STATUS));
                GymStatus gymStatus1 = GymStatus.valueOf(gymStatus);
                int calories = cursor.getInt(cursor.getColumnIndexOrThrow(TrackerContract.DayTrackEntry.COLUMN_NAME_CALORIES));
                int water = cursor.getInt(cursor.getColumnIndexOrThrow(TrackerContract.DayTrackEntry.COLUMN_NAME_WATER));
                String note = cursor.getString(cursor.getColumnIndexOrThrow(TrackerContract.DayTrackEntry.COLUMN_NAME_NOTE));
                map.put(date, new DayTrackBuilder().withCalories(calories).withWater(water).withNote(note).withGymStatus(gymStatus1).withLocalDate(date).build());
            }
        }catch (Exception e){
            Timber.e(e);
        }
        return map;
        }
}
