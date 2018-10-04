package com.example.yevstaf.checkthesun.adapter_factories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.example.yevstaf.checkthesun.R;
import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunset;
import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunsetItem;
import com.example.yevstaf.checkthesun.http_services.Item;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Vladyslav on 24.09.2018.
 */

public class InfoCardsAdaptersFactory implements AdaptersFactory {
    public static final int DEFAULT_ADAPTER = 0;
    Context context;

    public InfoCardsAdaptersFactory(Context context) {
        this.context = context;
    }

    @Override
    public SimpleAdapter getAdapter(int mod) {
        switch (mod){
            case (DEFAULT_ADAPTER):
                return makeInfoCardAdapterUsingSunriseSunsetAPI();

        }
        return null;
    }

    private SimpleAdapter makeInfoCardAdapterUsingSunriseSunsetAPI() {
        SimpleAdapter adapter = null;
        try {
            String from[] = {"Sunrise", "Sunset", "DayLength","Place"};
            int[] to = {R.id.tv_sunrise, R.id.tv_sunset, R.id.tv_day_length,R.id.tv_place};
            ArrayList<Map<String, Object>> dataForAdapter = selectDataForAdapter();
            adapter = new SimpleAdapter(context,dataForAdapter,R.layout.list_item_info_card,from,to);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return adapter;
    }




    private ArrayList<Map<String, Object>> selectDataForAdapter() throws JSONException {
        ArrayList<Map<String, Object>> dataForAdapter = new ArrayList<>();

        SQLiteDatabase database = getMarkersDB();
        LinkedList<Item> items = getItemsFromDB(database);

        Iterator<Item> iterator = items.iterator();
        while(iterator.hasNext()){
            Item each = iterator.next();
            HashMap<String,Object> viewData = sunriseSunsetDataIntoMap(each);
            dataForAdapter.add(viewData);
        }
        return dataForAdapter;
    }

    private SQLiteDatabase getMarkersDB(){
        SQLiteOpenHelper helper = new MarkersDataBase(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        return database;
    }

    private Cursor selectAllRows(SQLiteDatabase database){
        Cursor cursor =  database.rawQuery("Select * from " + MarkersDataBase.TABLE_MARKERS_NAME, null);
        return cursor;
    }

    private LinkedList<Item> getItemsFromDB(SQLiteDatabase database){
        LinkedList<Item> items = new LinkedList<>();
        SunriseSunset sunriseSunset = new SunriseSunset(context);
        Cursor cursor = selectAllRows(database);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                LatLng coordinates = getLatLngFromCursor(cursor);
                SunriseSunsetItem item = sunriseSunset.getItem(coordinates);
                items.add(item);
            } while (cursor.moveToNext());
        }else{
            Toast.makeText(context,"No place was selected",Toast.LENGTH_LONG);
        }
        return items;
    }

    private LatLng getLatLngFromCursor(Cursor cursor){
        Double latitude = cursor.getDouble(cursor.getColumnIndex(MarkersDataBase.TABLE_MARKERS_ROW_LATITUDE));
        Double longitude = cursor.getDouble(cursor.getColumnIndex(MarkersDataBase.TABLE_MARKERS_ROW_LONGITUDE));
        LatLng coordinates = new LatLng(latitude, longitude);
        return coordinates;
    }

    private HashMap<String,Object> sunriseSunsetDataIntoMap(Item each) throws JSONException {
        String sunrise = each.getString(SunriseSunset.LINE_SUNRISE);
        String sunset = each.getString(SunriseSunset.LINE_SUNSET);
        String dayLength = each.getString(SunriseSunset.LINE_DAY_LENGTH);
        HashMap<String,Object> map = new HashMap<>();
        map.put("Sunrise",sunrise);
        map.put("Sunset",sunset);
        map.put("DayLength",dayLength);

        return map;
    }


}
