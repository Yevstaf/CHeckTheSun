package com.example.yevstaf.checkthesun.google_map_events;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Vladyslav on 15.09.2018.
 */

public class LoadMarkersFromDatabaseEvent implements LoadMarkersEvent {
    Context context;

    public LoadMarkersFromDatabaseEvent(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent(GoogleMap googleMap) {
        MarkersDataBase helper = new MarkersDataBase(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + MarkersDataBase.TABLE_MARKERS_NAME,null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0) {
            do {
                Double latitude = cursor.getDouble(cursor.getColumnIndex(MarkersDataBase.TABLE_MARKERS_ROW_LATITUDE));
                Double longitude = cursor.getDouble(cursor.getColumnIndex(MarkersDataBase.TABLE_MARKERS_ROW_LONGITUDE));
                String description = cursor.getString(cursor.getColumnIndex(MarkersDataBase.TABLE_MARKERS_ROW_DESCRIPTION));
                MarkerOptions options = new MarkerOptions();
                LatLng coordinates = new LatLng(latitude, longitude);
                options.position(coordinates);
                options.title(description);
                googleMap.addMarker(options);
            } while (cursor.moveToNext());
        }
    }
}
