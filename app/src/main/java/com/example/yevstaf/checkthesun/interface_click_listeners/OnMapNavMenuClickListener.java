package com.example.yevstaf.checkthesun.interface_click_listeners;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.yevstaf.checkthesun.R;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventClearMarkers;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventGoToDevelopersPage;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventPlaceMarker;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventSaveMarkerToDB;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventStartInfoCardsActivity;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEvet;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnMapNavMenuClickListener implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    GoogleMap googleMap;
    ArrayList<OnNavMenuClickEvet> infoEvents;
    ArrayList<OnNavMenuClickEvet> clearEvents;
    ArrayList<OnNavMenuClickEvet> myLocationEvents;
    ArrayList<OnNavMenuClickEvet> shareEvents;

    public OnMapNavMenuClickListener(Context context, GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.context = context;
        infoEvents = new ArrayList<>();
        clearEvents = new ArrayList<>();
        myLocationEvents = new ArrayList<>();
        shareEvents = new ArrayList<>();
        addAllNeededEvents();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_info) {
            runInfoIvents();
        } else if (id == R.id.nav_clear) {
            runClearEvents();
        } else if (id == R.id.nav_my_location) {
            runMyLocationIvents();
        }else if (id == R.id.nav_share) {
            runShareEvents();
        }

        DrawerLayout drawer = ((AppCompatActivity) context).findViewById(R.id.drawer_layout_map);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addAllNeededEvents() {
        clearEvents.add(new OnNavMenuClickEventClearMarkers(context));
        infoEvents.add(new OnNavMenuClickEventStartInfoCardsActivity(context));
        myLocationEvents.add(new OnNavMenuClickEventSaveMarkerToDB(context));
        myLocationEvents.add(new OnNavMenuClickEventPlaceMarker(context,googleMap));
        shareEvents.add(new OnNavMenuClickEventGoToDevelopersPage(context));
    }

    protected void runClearEvents() {
        for (OnNavMenuClickEvet each : clearEvents) {
            each.runEvent();
        }
    }

    protected void runInfoIvents() {
        for (OnNavMenuClickEvet each : infoEvents) {
            each.runEvent();
        }
    }

    protected void runMyLocationIvents() {
        for (OnNavMenuClickEvet each : myLocationEvents) {
            each.runEvent();
        }
    }
    protected void runShareEvents() {
        for (OnNavMenuClickEvet each : shareEvents) {
            each.runEvent();
        }
    }
}
