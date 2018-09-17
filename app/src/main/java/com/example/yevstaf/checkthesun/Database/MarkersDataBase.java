package com.example.yevstaf.checkthesun.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Vladyslav on 11.09.2018.
 */

public class MarkersDataBase extends SQLiteOpenHelper{
   public static final String DB_NAME = "Markers DB";
   public static final String TABLE_MARKERS_NAME = "Markers";
   public static final String TABLE_MARKERS_ROW_ID = "id";
   public static final String TABLE_MARKERS_ROW_DESCRIPTION = "description";
   public static final String TABLE_MARKERS_ROW_LATITUDE = "latitude";
   public static final String TABLE_MARKERS_ROW_LONGITUDE = "longitude";


    public final int VERSION = 1;
    public MarkersDataBase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_MARKERS_NAME + " ("
        + TABLE_MARKERS_ROW_ID + " integer primary key autoincrement, "
        + TABLE_MARKERS_ROW_DESCRIPTION + " text, "
        + TABLE_MARKERS_ROW_LATITUDE + " real, "
        + TABLE_MARKERS_ROW_LONGITUDE + " real" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
