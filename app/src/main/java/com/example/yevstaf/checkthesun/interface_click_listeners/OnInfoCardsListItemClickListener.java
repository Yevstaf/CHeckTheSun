package com.example.yevstaf.checkthesun.interface_click_listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import com.example.yevstaf.checkthesun.R;

/**
 * Created by Vladyslav on 04.10.2018.
 */

public class OnInfoCardsListItemClickListener implements AdapterView.OnItemClickListener {
    protected boolean isDetailsActive;
    public OnInfoCardsListItemClickListener() {
        isDetailsActive = false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
