package com.example.yevstaf.checkthesun.google_map_events;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Vladyslav on 15.09.2018.
 */

public interface LoadMarkersEvent {
    void runEvent(GoogleMap googleMap);
}
