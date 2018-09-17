package com.example.yevstaf.checkthesun.interface_click_listeners;

import android.content.Context;
import android.view.View;

import com.example.yevstaf.checkthesun.interface_events.OnMapMenuClickEvent;
import com.example.yevstaf.checkthesun.interface_events.OnMapMenuClickEventNoMarkers;
import com.example.yevstaf.checkthesun.interface_events.OnMapMenuClickEventSunriseSunsetInfo;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by Vladyslav on 12.09.2018.
 */

public class OnFabClickListeners implements View.OnClickListener {
    private Context context;
    private View fab;

    GoogleMap googleMap;
    ArrayList<OnMapMenuClickEvent> events;
    public OnFabClickListeners(Context context,View fab, GoogleMap googleMap) {
        this.context = context;
        this.googleMap = googleMap;
        this.fab = fab;
        events = new ArrayList<>();
        addNeededEvents();
    }

    @Override
    public void onClick(View view) {
        for(OnMapMenuClickEvent each : events){
            each.runEvent(googleMap);
        }
    }
    private void addNeededEvents(){
        events.add(new OnMapMenuClickEventSunriseSunsetInfo(context));
    }

}
