package com.example.yevstaf.checkthesun.google_map_events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vladyslav on 15.09.2018.
 */

public class OnPlaceSelectedEventSaveMarkerToDB implements OnPlaceSelectedEvent {
    Context context;

    public OnPlaceSelectedEventSaveMarkerToDB(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent(GoogleMap googleMap, Place place) {
        LatLng coordinates = place.getLatLng();
        SQLiteDatabase database = getDB();
        Double latitude = coordinates.latitude;
        Double longitude = coordinates.longitude;
        String sql = "insert into " + MarkersDataBase.TABLE_MARKERS_NAME + " (" +
                MarkersDataBase.TABLE_MARKERS_ROW_DESCRIPTION + ", " +
                MarkersDataBase.TABLE_MARKERS_ROW_LATITUDE + "," +
                MarkersDataBase.TABLE_MARKERS_ROW_LONGITUDE + ") " +
                "values (" +
                "'regular marker'," + latitude + "," + longitude + ");";

        database.execSQL(sql);
    }

    private SQLiteDatabase getDB(){
        MarkersDataBase helper = new MarkersDataBase(context);
        return helper.getWritableDatabase();
    }
}
