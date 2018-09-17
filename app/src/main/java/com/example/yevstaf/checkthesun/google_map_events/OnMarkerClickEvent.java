package com.example.yevstaf.checkthesun.google_map_events;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public interface OnMarkerClickEvent {
    boolean runEvent(Marker marker,GoogleMap googleMap);
}
