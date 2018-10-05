package com.example.yevstaf.checkthesun.google_map_services;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yevstaf.checkthesun.Database.LocationDatabaseOpenHelper;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class LocationServise {
    Context context;

    public LocationServise(Context context) {
        this.context = context;
    }

    public LatLng getMyCoordinates(){
        SQLiteDatabase database = new LocationDatabaseOpenHelper(context).getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + LocationDatabaseOpenHelper.TABLE_LOCATION_NAME,null);
        LatLng coordinaates = null;
        if(cursor.moveToFirst()) {

            do {
                double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                coordinaates = new LatLng(latitude,longitude);
            }while(cursor.moveToNext());
        }
        return coordinaates;
    }


}

