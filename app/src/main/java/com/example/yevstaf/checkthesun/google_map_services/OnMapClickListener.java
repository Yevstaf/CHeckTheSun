package com.example.yevstaf.checkthesun.google_map_services;

import android.content.Context;
import android.util.Log;

import com.example.yevstaf.checkthesun.google_map_events.OnMapClickEvent;
import com.example.yevstaf.checkthesun.google_map_events.OnMapClickEventPlaceMapMarker;
import com.example.yevstaf.checkthesun.google_map_events.OnMapClickEventSaveMarkerToDB;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class OnMapClickListener implements GoogleMap.OnMapClickListener {
        GoogleMap googleMap;
        Context context;
        List<OnMapClickEvent> eventsList = new ArrayList<>();
    public OnMapClickListener(Context context,GoogleMap googleMap){
        this.googleMap = googleMap;
        this.context = context;
        addRequiredEvents();

    }
    @Override
    public void onMapClick(LatLng latLng) {
        Log.v("SunriseSunset"," OnMarkerClockListener: lat = " + latLng.latitude + " longitude = " + latLng.longitude);
        for(OnMapClickEvent event : eventsList){
            event.runEvent(googleMap,latLng);
        }
    }

    public void addRequiredEvents(){

        eventsList.add(new OnMapClickEventPlaceMapMarker());
        eventsList.add(new OnMapClickEventSaveMarkerToDB(context));
    }
}
