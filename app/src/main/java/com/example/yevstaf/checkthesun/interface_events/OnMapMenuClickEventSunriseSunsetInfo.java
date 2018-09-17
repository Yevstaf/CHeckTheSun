package com.example.yevstaf.checkthesun.interface_events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.yevstaf.checkthesun.InfoCardsActivity;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Vladyslav on 11.09.2018.
 */

public class OnMapMenuClickEventSunriseSunsetInfo implements OnMapMenuClickEvent {
    Context context;

    public OnMapMenuClickEventSunriseSunsetInfo(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent(GoogleMap gMap) {
        startInformationActivity();
    }

    private void startInformationActivity(){
        Intent intent = new Intent(context,InfoCardsActivity.class);
        context.startActivity(intent);
    }
}
