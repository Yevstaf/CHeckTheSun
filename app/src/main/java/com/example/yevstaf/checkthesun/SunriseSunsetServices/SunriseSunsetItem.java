package com.example.yevstaf.checkthesun.SunriseSunsetServices;

import com.example.yevstaf.checkthesun.http_services.Item;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vladyslav on 29.07.2018.
 */

public class SunriseSunsetItem extends Item {
    private String KEY = "results";

    public SunriseSunsetItem(String object) {
        super(object);
    }

    @Override
    public String getString(String key) throws JSONException {
        JSONObject basicObject = super.getJsonObject();
        JSONObject informativeObject = getInformativePart(basicObject);
        return informativeObject.getString(key);
    }

    private JSONObject getInformativePart(JSONObject object) throws JSONException {
        return object.getJSONObject(KEY);
    }

//    public String getSunrise() throws JSONException {
//        return getString(SunriseSunset.LINE_SUNRISE);
//    }
//
//    public String getSunset() throws JSONException {
//        return getString(SunriseSunset.LINE_SUNSET);
//    }
//
//    public String getSolarNoon() throws JSONException {
//        return getString(SunriseSunset.LINE_SOLAR_NOON);
//    }

}
