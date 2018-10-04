package com.example.yevstaf.checkthesun.SunriseSunsetServices;

import android.content.Context;

import com.example.yevstaf.checkthesun.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vladyslav on 30.07.2018.
 */

public class SunriseSunset {
    public SunriseSunset(Context context){
        this.context = context;
    }

        Context context;
        public static final String LINE_SUNRISE = "sunrise";
        public static final String LINE_SUNSET = "sunset";
        public static final String LINE_SOLAR_NOON = "solar_noon";
        public static final String LINE_DAY_LENGTH = "day_length";
        public static final String LINE_CIVIL_TWILIGHT_BEGIN = "civil_twilight_begin";
        public static final String LINE_CIVIL_TWILIGHT_END = "civil_twilight_end";
        public static final String LINE_NAUTICAL_TWILIGHT_BEGIN = "nautical_twilight_begin";
        public static final String LINE_NAUTICAL_TWILIGHT_END = "nautical_twilight_end";
        public static final String LINE_ASTRONOMICAL_TWILIGHT_BEGIN = "astronomical_twilight_begin";
        public static final String LINE_ASTRONOMICAL_TWILIGHT_END = "astronomical_twilight_end";
    public SunriseSunsetItem getItem(LatLng coordinates) {
            String url = makeURL(coordinates);
            SunriseSunsetGETRequest request = new SunriseSunsetGETRequest();
            String respondLine = request.executeGetReauest(url);
        return new SunriseSunsetItem(respondLine);
    }

    private String getBasicURL(){
            String basicURL = context.getResources().getString(R.string.httpRequestSunriseSunset);
        return basicURL;
    }

    private String makeURL(LatLng coordinates){
            String basicURL = getBasicURL();
            String url = basicURL + "lat=" + coordinates.latitude + "&" + "lng=" + coordinates.longitude;
        return url;
    }



}
