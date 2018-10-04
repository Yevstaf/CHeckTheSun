package com.example.yevstaf.checkthesun.interface_events;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yevstaf.checkthesun.R;

/**
 * Created by Vladyslav on 04.10.2018.
 */

public class OnListIlemClickEventOpenOrCloseDetails implements OnListItemClickEvent {
    protected boolean isDetailsActive;
    public OnListIlemClickEventOpenOrCloseDetails() {
        isDetailsActive = false;
    }
    @Override
    public void runEvent(View view) {
        LinearLayout detailsLayout = view.findViewById(R.id.details_layout);
        changeLayoutVisibility(detailsLayout);
        changeLayoutLooks(view);

    }
    protected void changeLayoutVisibility(LinearLayout layout){
        if(isDetailsActive){
            layout.setVisibility(View.GONE);
            isDetailsActive = false;
        }else{
            layout.setVisibility(View.VISIBLE);
            isDetailsActive = true;
        }
    }
    protected void changeLayoutLooks(View view){
        if(isDetailsActive){
            int color = view.getResources().getColor(R.color.black_transparent);
            LinearLayout bottomLayout = view.findViewById(R.id.bottom_layout);
            bottomLayout.setBackgroundColor(color);
        }else{
            LinearLayout bottomLayout = view.findViewById(R.id.bottom_layout);
            bottomLayout.setBackgroundColor(Color.WHITE);
        }
    }
}
