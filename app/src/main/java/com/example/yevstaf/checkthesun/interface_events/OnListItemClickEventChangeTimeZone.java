package com.example.yevstaf.checkthesun.interface_events;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yevstaf.checkthesun.R;
import com.example.yevstaf.checkthesun.adapter_factories.AdaptersFactory;
import com.example.yevstaf.checkthesun.adapter_factories.InfoCardsListAdaptersFactory;

/**
 * Created by Vladyslav on 04.10.2018.
 */

public class OnListItemClickEventChangeTimeZone implements OnListItemClickEvent {
    Context context;

    public OnListItemClickEventChangeTimeZone(Context context) {
        this.context = context;
    }

    @Override
    public void runEvent(View view) {
        String timeZone = ((TextView)view).getText().toString();
        ListView lvInfoCards = ((AppCompatActivity)context).findViewById(R.id.lvInfoCards);
        changeListViewAdapterAccordingToTimeZone(lvInfoCards,timeZone);
    }
    protected void changeListViewAdapterAccordingToTimeZone(ListView listView, String timeZone){
        InfoCardsListAdaptersFactory factory = new InfoCardsListAdaptersFactory(context);
        factory.setTimeZone(timeZone);
        SimpleAdapter adapter = factory.getAdapter(InfoCardsListAdaptersFactory.DEFAULT_ADAPTER);
        listView.setAdapter(adapter);
    }
}
