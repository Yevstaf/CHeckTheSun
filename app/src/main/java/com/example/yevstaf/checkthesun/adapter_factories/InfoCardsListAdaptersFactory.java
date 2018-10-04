package com.example.yevstaf.checkthesun.adapter_factories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.example.yevstaf.checkthesun.R;
import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunset;
import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunsetItem;
import com.example.yevstaf.checkthesun.http_services.Item;
import com.example.yevstaf.checkthesun.time_zone_servise.TimeZoneCorrector;
import com.google.android.gms.maps.model.LatLng;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Vladyslav on 24.09.2018.
 * Missing time zone validator, be careful to write correctly
 */

public class InfoCardsListAdaptersFactory implements AdaptersFactory {

    public static final int DEFAULT_ADAPTER = 0;
    public static final String VIEW_SUNRISE = "Sunrise";
    public static final String VIEW_SUNSET = "Sunset";
    public static final String VIEW_SOLAR_NOON = "Solar noon";
    public static final String VIEW_DAY_LENGTH = "Day length";
    public static final String VIEW_CIVIL_TWILIGHT_BEGIN = "Civil twilight begin";
    public static final String VIEW_CIVIL_TWLIGHT_END = "Civil twilight end";
    public static final String VIEW_NAUTICAL_TWILIGHT_BEGIN = "Nautical twilight begin";
    public static final String VIEW_NAUTICAL_TWILIGHT_END = "Nautical twilight end";
    public static final String VIEW_ASTRONOMICAL_TWILIGHT_BEGIN = "Astronomical twilight begin";
    public static final String VIEW_ASTRONOMICAL_TWILIGHT_END = "Astronomical twilight end";
    private Context context;
    private static String timeZone;

    public static void setTimeZone(String timeZone) {
        InfoCardsListAdaptersFactory.timeZone = timeZone;
    }

    public InfoCardsListAdaptersFactory(Context context) {
        this.context = context;
        timeZone = "GMT";
    }


    @Override
    public SimpleAdapter getAdapter(int mod) {
        switch (mod){
            case (DEFAULT_ADAPTER):
                SimpleAdapter adapter = null;
                    try {
                        adapter = makeInfoCardAdapterUsingSunriseSunsetAPI();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                return adapter;

        }
        return null;
    }

    private SimpleAdapter makeInfoCardAdapterUsingSunriseSunsetAPI() throws JSONException {
        SimpleAdapter adapter;
            String from[] =
                    {VIEW_SUNRISE,
                            VIEW_SUNSET,
                            VIEW_DAY_LENGTH,
                            VIEW_SOLAR_NOON,
                            VIEW_CIVIL_TWILIGHT_BEGIN,
                            VIEW_CIVIL_TWLIGHT_END,
                            VIEW_ASTRONOMICAL_TWILIGHT_BEGIN,
                            VIEW_ASTRONOMICAL_TWILIGHT_END,
                            VIEW_NAUTICAL_TWILIGHT_BEGIN,
                            VIEW_NAUTICAL_TWILIGHT_END};
            int[] to = {R.id.tv_sunrise,
                    R.id.tv_sunset,
                    R.id.tv_day_length,
                    R.id.tv_solar_noon_info,
                    R.id.tv_civil_twilight_begin_info,
                    R.id.tv_civil_twilight_end_info,
                    R.id.tv_astronomical_twilight_begin_info,
                    R.id.tv_astronomical_twilight_end_info,
                    R.id.tv_nautical_twilight_begin_info,
                    R.id.tv_nautical_twilight_end_info};

            ArrayList<Map<String, Object>> dataForAdapter = selectDataForAdapter();
            adapter = new SimpleAdapter(context,dataForAdapter,R.layout.list_item_info_card,from,to);
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
        String sn = each.getString(SunriseSunset.LINE_SOLAR_NOON);
        String ctb = each.getString(SunriseSunset.LINE_CIVIL_TWILIGHT_BEGIN);
        String cte = each.getString(SunriseSunset.LINE_CIVIL_TWILIGHT_END);
        String atb = each.getString(SunriseSunset.LINE_ASTRONOMICAL_TWILIGHT_BEGIN);;
        String ate = each.getString(SunriseSunset.LINE_ASTRONOMICAL_TWILIGHT_END);
        String ntb = each.getString(SunriseSunset.LINE_NAUTICAL_TWILIGHT_BEGIN);
        String nte = each.getString(SunriseSunset.LINE_NAUTICAL_TWILIGHT_END);

        HashMap<String,Object> map = new HashMap<>();
        map.put(VIEW_SUNRISE,sunrise);
        map.put(VIEW_SUNSET,sunset);
        map.put(VIEW_SOLAR_NOON,sn);
        map.put(VIEW_CIVIL_TWILIGHT_BEGIN,ctb);
        map.put(VIEW_CIVIL_TWLIGHT_END,cte);
        map.put(VIEW_ASTRONOMICAL_TWILIGHT_BEGIN,atb);
        map.put(VIEW_ASTRONOMICAL_TWILIGHT_END,ate);
        map.put(VIEW_NAUTICAL_TWILIGHT_BEGIN,ntb);
        map.put(VIEW_NAUTICAL_TWILIGHT_END,nte);
        correctTimeAccordingToTimeZone(map);
        map.put(VIEW_DAY_LENGTH,dayLength); // day length has a different format
        return map;

    }

    protected HashMap<String,Object> correctTimeAccordingToTimeZone(HashMap<String,Object> map){
        for(String key : map.keySet()){
            TimeZoneCorrector corrector = new TimeZoneCorrector(timeZone);
            String each = (String)map.get(key);
            each = corrector.correctTimeZone(each);
            map.put(key,each);
        }
        return map;
    }


}
