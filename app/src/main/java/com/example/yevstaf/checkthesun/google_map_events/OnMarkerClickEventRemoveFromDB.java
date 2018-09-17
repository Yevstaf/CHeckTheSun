package com.example.yevstaf.checkthesun.google_map_events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Vladyslav on 12.09.2018.
 */

public class OnMarkerClickEventRemoveFromDB implements OnMarkerClickEvent{
    Context context;

    public OnMarkerClickEventRemoveFromDB(Context context) {
        this.context = context;
    }

    @Override
    public boolean runEvent(Marker marker, GoogleMap googleMap) {
        if(marker != null & googleMap != null) {
            removeFromDatabase(marker);
            return true;
        }
        return false;
    }
    private void removeFromDatabase(Marker marker){
        LatLng coordinates = marker.getPosition();
        removeByLatLng(coordinates);
    }
    private void removeByLatLng(LatLng coordinates){
        Double latitude = coordinates.latitude;
        Double longitude = coordinates.longitude;
        String sql = "delete from " + MarkersDataBase.TABLE_MARKERS_NAME
                + " where " + MarkersDataBase.TABLE_MARKERS_ROW_LATITUDE + " = " + latitude
                + " and " + MarkersDataBase.TABLE_MARKERS_ROW_LONGITUDE + " = " + longitude;
        SQLiteDatabase database = getDatabase();
        database.execSQL(sql);
    }

    private SQLiteDatabase getDatabase(){
        SQLiteOpenHelper dbHelper = new MarkersDataBase(context);
        return dbHelper.getWritableDatabase();
    }
}
