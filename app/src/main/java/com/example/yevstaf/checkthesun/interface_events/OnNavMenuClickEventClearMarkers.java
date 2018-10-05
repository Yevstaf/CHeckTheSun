package com.example.yevstaf.checkthesun.interface_events;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnNavMenuClickEventClearMarkers implements OnNavMenuClickEvet {
    private Context context;

    public OnNavMenuClickEventClearMarkers(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent() {
       clearDatabase();
       restartActivity();
    }

    protected void clearDatabase(){
        SQLiteDatabase database = new MarkersDataBase(context).getWritableDatabase();
        database.delete(MarkersDataBase.TABLE_MARKERS_NAME,null,null);
    }

    protected void restartActivity(){
        AppCompatActivity activity = ((AppCompatActivity)context);
        activity.finish();
        activity.startActivity(activity.getIntent());
    }
}
