package com.example.yevstaf.checkthesun.adapter_factories;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Vladyslav on 04.10.2018.
 */

public class TimeZoneSpinnerAdaptersFactory {
    Context context;
    public static final int DEFAULT_ADAPTER = 0;

    public TimeZoneSpinnerAdaptersFactory(Context context) {
        this.context = context;
    }


    public ArrayAdapter<String> getAdapter(int mod) {
        switch (mod){
            case(DEFAULT_ADAPTER):
                ArrayAdapter<String> adapter = adapterWithAvaliablesTimeZones();
                return adapter;
        }
        return null;
    }

    public ArrayAdapter<String> adapterWithAvaliablesTimeZones(){
            List<String> list = new ArrayList<>();
            String[] timeZones = TimeZone.getAvailableIDs();
            for(String each : timeZones){
                list.add(each);
                Log.v("Time zone:",each);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item,list);
            return adapter;
        }


}
