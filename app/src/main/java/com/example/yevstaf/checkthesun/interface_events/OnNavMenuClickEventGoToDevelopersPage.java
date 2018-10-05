package com.example.yevstaf.checkthesun.interface_events;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.example.yevstaf.checkthesun.R;

/**
 * Created by Vladyslav on 05.10.2018.
 */

public class OnNavMenuClickEventGoToDevelopersPage implements OnNavMenuClickEvet{
    Context context;

    public OnNavMenuClickEventGoToDevelopersPage(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent() {
        String url = context.getResources().getString(R.string.author_url);
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        context.startActivity(launchBrowser);
    }
}
