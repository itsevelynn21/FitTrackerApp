package com.gymapp.main.database;

import android.provider.BaseColumns;

public final class TrackerContract {

    private TrackerContract(){}

    public static class DayTrackEntry implements BaseColumns {
        public static final String TABLE_NAME = "daytrack";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_CALORIES = "calories";
        public static final String COLUMN_NAME_WATER = "water";
        public static final String COLUMN_NAME_GYM_STATUS = "gymstatus";
        public static final String COLUMN_NAME_NOTE = "note";

    }

}
