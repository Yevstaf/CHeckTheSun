package com.example.yevstaf.checkthesun.google_map_events;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public interface OnMapClickEvent {
    void runEvent(GoogleMap map, LatLng coordinates);
}
