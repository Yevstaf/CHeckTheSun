package com.example.yevstaf.checkthesun.interface_events;

import android.content.Context;
import android.content.Intent;

import com.example.yevstaf.checkthesun.InfoCardsActivity;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnNavMenuClickEventStartInfoCardsActivity implements OnNavMenuClickEvet {
    Context context;

    public OnNavMenuClickEventStartInfoCardsActivity(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent() {
        startInfoCardsActivity();
    }

    protected void startInfoCardsActivity(){
        Intent intent = new Intent(context, InfoCardsActivity.class);
        context.startActivity(intent);
    }
}
