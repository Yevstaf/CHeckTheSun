package com.example.yevstaf.checkthesun.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class LocationDatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Location DB";
    public static final String TABLE_LOCATION_NAME = "Locations";
    public static final String TABLE_LOCATION_ROW_ID = "id";
    public static final String TABLE_LOCATION_ROW_LATITUDE = "latitude";
    public static final String TABLE_LOCATION_ROW_LONGITUDE = "longitude";

    public LocationDatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_LOCATION_NAME + " ("
                + TABLE_LOCATION_ROW_ID + " integer primary key autoincrement, "
                + TABLE_LOCATION_ROW_LATITUDE + " real, "
                + TABLE_LOCATION_ROW_LONGITUDE + " real" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
