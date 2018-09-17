package com.example.yevstaf.checkthesun.google_map_services;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class LocationCallback extends com.google.android.gms.location.LocationCallback {
    @Override
    public void onLocationResult(LocationResult locationResult) {
        List<Location> locationList = locationResult.getLocations();
        if (locationList.size() > 0) {
            //The last location in the list is the newest
            Location location = locationList.get(locationList.size() - 1);
            //Place current location marker
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            //starting database from here because we need user's location coordinates with user's location
            Log.v("Database: " ,latLng.toString());
            //move map camera

        }
    }
}
