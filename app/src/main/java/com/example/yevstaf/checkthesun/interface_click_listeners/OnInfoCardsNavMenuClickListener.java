package com.example.yevstaf.checkthesun.interface_click_listeners;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yevstaf.checkthesun.R;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventClearMarkers;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEventGoToDevelopersPage;
import com.example.yevstaf.checkthesun.interface_events.OnNavMenuClickEvet;

import java.util.ArrayList;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnInfoCardsNavMenuClickListener implements NavigationView.OnNavigationItemSelectedListener {
    Context context;
    ArrayList<OnNavMenuClickEvet> clearEvents;
    ArrayList<OnNavMenuClickEvet> shareEvents;
    public OnInfoCardsNavMenuClickListener(Context context) {
        this.context = context;
        clearEvents = new ArrayList<>();
        shareEvents = new ArrayList<>();
        addAllNeededEvents();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

         if (id == R.id.nav_delete) {
             Toast.makeText(context,"No selected markers",Toast.LENGTH_SHORT).show();
            runClearEvents();
        }else if (id == R.id.nav_share) {
             runShareEvents();
         }

        DrawerLayout drawer = ((AppCompatActivity) context).findViewById(R.id.drawer_layout_info_cards);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void addAllNeededEvents(){
        clearEvents.add(new OnNavMenuClickEventClearMarkers(context));
        shareEvents.add(new OnNavMenuClickEventGoToDevelopersPage(context));
    }

    protected void runClearEvents() {
        for (OnNavMenuClickEvet each : clearEvents) {
            each.runEvent();
        }
    }
    protected void runShareEvents() {
        for (OnNavMenuClickEvet each : shareEvents) {
            each.runEvent();
        }
    }

}
