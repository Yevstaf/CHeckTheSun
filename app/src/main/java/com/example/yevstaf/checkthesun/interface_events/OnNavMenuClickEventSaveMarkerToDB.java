package com.example.yevstaf.checkthesun.interface_events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.example.yevstaf.checkthesun.google_map_services.LocationServise;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnNavMenuClickEventSaveMarkerToDB implements OnNavMenuClickEvet {
    Context context;

    public OnNavMenuClickEventSaveMarkerToDB(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent() {
        LocationServise servise = new LocationServise(context);
        LatLng coordinates = servise.getMyCoordinates();
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
