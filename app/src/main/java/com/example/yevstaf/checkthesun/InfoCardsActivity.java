package com.example.yevstaf.checkthesun;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.yevstaf.checkthesun.adapter_factories.InfoCardsListAbstractFactory;
import com.example.yevstaf.checkthesun.adapter_factories.TimeZoneSpinnerAdaptersFactory;
import com.example.yevstaf.checkthesun.interface_click_listeners.OnInfoCardsListItemClickListener;
import com.example.yevstaf.checkthesun.interface_click_listeners.OnInfoCardsNavMenuClickListener;
import com.example.yevstaf.checkthesun.interface_click_listeners.OnTimeZonesSpinnerClickListener;

import java.net.URL;

public class InfoCardsActivity extends AppCompatActivity {
    private final String TAG = "SunriseSunset";
    ListView lvInfoCards;
    //SimpleAdapter adapter;
    Spinner timeZones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_info_cards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_info_cards);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_info_cards);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_info_cards);
        navigationView.setNavigationItemSelectedListener(new OnInfoCardsNavMenuClickListener(this));
            this.setTitle(R.string.activity_info_cards_title);

        lvInfoCards = findViewById(R.id.lvInfoCards);
        timeZones = findViewById(R.id.spinner_time_zone);

        fillSpinner();
        configureClickListeners();
    }
    private void fillSpinner(){
        Spinner spinner = findViewById(R.id.spinner_time_zone);
        ArrayAdapter adapter = getSpinnerAdapterFromFactory();
        if(adapter != null)
            spinner.setAdapter(adapter);
    }
    private ArrayAdapter getSpinnerAdapterFromFactory(){
        TimeZoneSpinnerAdaptersFactory factory = new TimeZoneSpinnerAdaptersFactory(this);
        ArrayAdapter adapter = factory.getAdapter(TimeZoneSpinnerAdaptersFactory.DEFAULT_ADAPTER);
        return adapter;
    }

    private void configureClickListeners(){
        lvInfoCards.setOnItemClickListener(new OnInfoCardsListItemClickListener());
        lvInfoCards.setOnItemClickListener(new OnInfoCardsListItemClickListener());
        timeZones.setOnItemSelectedListener(new OnTimeZonesSpinnerClickListener(this));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_info_cards);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
