package com.example.yevstaf.checkthesun.google_map_services;

import android.content.Context;

import com.example.yevstaf.checkthesun.google_map_events.OnMarkerClickEvent;
import com.example.yevstaf.checkthesun.google_map_events.OnMarkerClickEventRemoveFromDB;
import com.example.yevstaf.checkthesun.google_map_events.OnMarkerClickEventRemoveMarker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class OnMarkerClickListener implements GoogleMap.OnMarkerClickListener {
   List<OnMarkerClickEvent> eventsList;
   GoogleMap googleMap;
   Context context;
    public OnMarkerClickListener(Context context, GoogleMap googleMap){
        this.context = context;
        this.googleMap = googleMap;
        eventsList = new ArrayList<>();
        addRequiredEvents();

    }
    @Override
    public boolean onMarkerClick(Marker marker) {
          for (OnMarkerClickEvent event : eventsList){
              if(event.runEvent(marker,googleMap) == false){
                  return false;

              }
          }
        return true;
    }

    public void addRequiredEvents(){
        eventsList.add(new OnMarkerClickEventRemoveMarker());
        eventsList.add(new OnMarkerClickEventRemoveFromDB(context));
    }
}
