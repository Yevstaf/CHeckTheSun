package com.example.yevstaf.checkthesun.adapter_factories;

import android.content.Context;
import android.widget.SimpleAdapter;
import com.example.yevstaf.checkthesun.R;

/**
 * Created by Vladyslav on 24.09.2018.
 */

public class InfoCardsListAbstractFactory {
    Context context;

    public InfoCardsListAbstractFactory(Context context) {
        this.context = context;
    }

    public SimpleAdapter selectAdapter(int viewId){
        AdaptersFactory factory = new InfoCardsListAdaptersFactory(context);
        SimpleAdapter adapter = null;
                switch(viewId) {
                    case R.id.lvInfoCards:
                        adapter = factory.getAdapter(InfoCardsListAdaptersFactory.DEFAULT_ADAPTER);
                        break;
                }
        return adapter;
    }
}
