package com.example.yevstaf.checkthesun.google_map_events;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class OnMarkerClickEventRemoveMarker implements OnMarkerClickEvent{

    @Override
    public boolean runEvent(Marker marker, GoogleMap googleMap) {
        if(marker != null){
            marker.remove();
            return true;
        }else {
            return false;
        }
    }
}
