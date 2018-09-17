package com.example.yevstaf.checkthesun.google_map_events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vladyslav on 11.09.2018.
 */

public class OnMapClickEventSaveMarkerToDB implements OnMapClickEvent{
    Context context;

    public OnMapClickEventSaveMarkerToDB(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent(GoogleMap map, LatLng coordinates) {
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
