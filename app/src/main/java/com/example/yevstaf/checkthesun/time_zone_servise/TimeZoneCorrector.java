package com.example.yevstaf.checkthesun.time_zone_servise;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Vladyslav on 04.10.2018.
 * Designed to translate time in GMT into time in chosen time zone
 */

public class TimeZoneCorrector {
    protected TimeZone timeZone;
    private SimpleDateFormat dateFormat;
    public TimeZoneCorrector(String timeZoneID) {
        this.timeZone = TimeZone.getTimeZone(timeZoneID);
        dateFormat = new SimpleDateFormat("hh:mm:ss aa", Locale.US);
    }
    public String correctTimeZone(String sourseDate){
        Date inputDate = parseDate(sourseDate);
        int differenceInTime = timeZone.getRawOffset();
        long outputMillis = inputDate.getTime() + differenceInTime;
        Date outputDate = new Date(outputMillis);
        String correctedDate = dateFormat.format(outputDate);
        return correctedDate;
    }

    protected Date parseDate(String sourseDate){
        Date date = null;
        try {

            date = dateFormat.parse(sourseDate);
            Log.e("Date handling"," Date correction successfull " + date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
