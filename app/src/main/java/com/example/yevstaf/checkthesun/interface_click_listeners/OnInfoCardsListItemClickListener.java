package com.example.yevstaf.checkthesun.interface_click_listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import com.example.yevstaf.checkthesun.R;
import com.example.yevstaf.checkthesun.interface_events.OnListIlemClickEventOpenOrCloseDetails;
import com.example.yevstaf.checkthesun.interface_events.OnListItemClickEvent;

import java.util.ArrayList;

/**
 * Created by Vladyslav on 04.10.2018.
 */

public class OnInfoCardsListItemClickListener implements AdapterView.OnItemClickListener {
    ArrayList<OnListItemClickEvent> events;

    public OnInfoCardsListItemClickListener() {
        this.events = new ArrayList<>();
        addAllNeededEvents();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        runEvents(view);
    }
    private void runEvents(View view){
        for(OnListItemClickEvent each : events){
            each.runEvent(view);
        }
    }
    private void addAllNeededEvents(){
        events.add(new OnListIlemClickEventOpenOrCloseDetails());
    }
}
