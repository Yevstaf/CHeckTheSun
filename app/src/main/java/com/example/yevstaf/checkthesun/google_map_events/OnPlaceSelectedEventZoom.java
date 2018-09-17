package com.example.yevstaf.checkthesun.google_map_events;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class OnPlaceSelectedEventZoom implements OnPlaceSelectedEvent {
    @Override
    public void runEvent(GoogleMap googleMap, Place place) {
        LatLng coordinates = place.getLatLng();
        googleMap.addMarker(new MarkerOptions().position(coordinates)
                .title(place.getName().toString()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 12));
    }
}
