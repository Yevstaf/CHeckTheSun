package com.example.yevstaf.checkthesun.google_map_services;

import android.content.Context;

import com.example.yevstaf.checkthesun.google_map_events.OnPlaceSelectedEvent;
import com.example.yevstaf.checkthesun.google_map_events.OnPlaceSelectedEventPlaceMarker;
import com.example.yevstaf.checkthesun.google_map_events.OnPlaceSelectedEventSaveMarkerToDB;
import com.example.yevstaf.checkthesun.google_map_events.OnPlaceSelectedEventZoom;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class OnPlaceSelectedListener implements PlaceSelectionListener {
    List<OnPlaceSelectedEvent> eventsList;
    GoogleMap googleMap;
    Context context;

    public OnPlaceSelectedListener(Context context,GoogleMap googleMap){
        this.googleMap = googleMap;
        this.context = context;
        eventsList = new ArrayList<>();
        addRequiredEvents();

    }
    @Override
    public void onPlaceSelected(Place place) {
        for(OnPlaceSelectedEvent event : eventsList){
            event.runEvent(googleMap,place);
        }
    }

    @Override
    public void onError(Status status) {

    }

    public void addRequiredEvents(){
        eventsList.add(new OnPlaceSelectedEventZoom());
        eventsList.add(new OnPlaceSelectedEventPlaceMarker());
        eventsList.add(new OnPlaceSelectedEventSaveMarkerToDB(context));
    }
}
