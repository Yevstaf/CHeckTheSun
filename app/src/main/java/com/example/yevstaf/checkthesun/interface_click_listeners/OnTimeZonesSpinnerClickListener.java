package com.example.yevstaf.checkthesun.interface_click_listeners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import com.example.yevstaf.checkthesun.interface_events.OnListItemClickEvent;
import com.example.yevstaf.checkthesun.interface_events.OnListItemClickEventChangeTimeZone;
import java.util.ArrayList;

/**
 * Created by Vladyslav on 04.10.2018.
 */

public class OnTimeZonesSpinnerClickListener implements AdapterView.OnItemSelectedListener{
    Context context;
    ArrayList<OnListItemClickEvent> events;
    public OnTimeZonesSpinnerClickListener(Context context) {
        this.context = context;
        events = new ArrayList<>();
        addAllNeededEvents();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        runEvents(view);
    }

    private void runEvents(View view){
        for(OnListItemClickEvent each : events){
            each.runEvent(view);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    protected void addAllNeededEvents(){
        events.add(new OnListItemClickEventChangeTimeZone(context));
    }

}
