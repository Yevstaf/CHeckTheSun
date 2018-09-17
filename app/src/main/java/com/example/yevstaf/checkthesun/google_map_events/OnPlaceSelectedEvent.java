package com.example.yevstaf.checkthesun.google_map_events;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public interface OnPlaceSelectedEvent {
  void runEvent(GoogleMap googleMap,Place place);
}
