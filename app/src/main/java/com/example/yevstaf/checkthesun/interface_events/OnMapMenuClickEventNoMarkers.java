package com.example.yevstaf.checkthesun.interface_events;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Vladyslav on 15.09.2018.
 */

public class OnMapMenuClickEventNoMarkers implements OnMapMenuClickEvent {
    View view;

    public OnMapMenuClickEventNoMarkers(View view) {
        this.view = view;
    }

    @Override
    public void runEvent(GoogleMap gMap) {
        Snackbar.make(view, "No selected markers found", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
