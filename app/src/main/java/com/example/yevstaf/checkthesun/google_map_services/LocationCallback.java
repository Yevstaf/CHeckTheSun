package com.example.yevstaf.checkthesun.google_map_services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

import com.example.yevstaf.checkthesun.Database.LocationDatabaseOpenHelper;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class LocationCallback extends com.google.android.gms.location.LocationCallback {
    Context context;

    public LocationCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        List<Location> locationList = locationResult.getLocations();
        if (locationList.size() > 0) {
            //The last location in the list is the newest
            Location location = locationList.get(locationList.size() - 1);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            saveLocationToDB(latLng);
        }
    }
    private void saveLocationToDB(LatLng coordinates){
        SQLiteDatabase database = new LocationDatabaseOpenHelper(context).getWritableDatabase();
        database.delete(LocationDatabaseOpenHelper.TABLE_LOCATION_NAME,null,null);
        Double latitude = coordinates.latitude;
        Double longitude = coordinates.longitude;
        String sql = "insert into " + LocationDatabaseOpenHelper.TABLE_LOCATION_NAME + " (" +
                LocationDatabaseOpenHelper.TABLE_LOCATION_ROW_LATITUDE + "," +
                LocationDatabaseOpenHelper.TABLE_LOCATION_ROW_LONGITUDE + ") " +
                "values (" + latitude + "," + longitude + ");";
        database.execSQL(sql);
    }
}
