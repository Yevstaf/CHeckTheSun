package com.example.yevstaf.checkthesun.interface_events;

import android.content.Context;

import com.example.yevstaf.checkthesun.google_map_services.LocationServise;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnNavMenuClickEventPlaceMarker implements OnNavMenuClickEvet {
    Context context;
    GoogleMap googleMap;

    public OnNavMenuClickEventPlaceMarker(Context context, GoogleMap googleMap) {
        this.context = context;
        this.googleMap = googleMap;
    }

    @Override
    public void runEvent() {
        LocationServise servise = new LocationServise(context);
        LatLng coordinates = servise.getMyCoordinates();
        MarkerOptions options = new MarkerOptions();
        options.position(coordinates);
        googleMap.addMarker(options);
    }
}
