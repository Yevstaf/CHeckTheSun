package com.example.yevstaf.checkthesun;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.yevstaf.checkthesun.Database.MarkersDataBase;
import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunset;
import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunsetItem;
import com.example.yevstaf.checkthesun.adapter_factories.ListViewAdaptersAbstractFactory;
import com.example.yevstaf.checkthesun.http_services.Item;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class InfoCardsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "SunriseSunset";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_info_cards);
        Toolbar toolbar = findViewById(R.id.toolbar_info_cards);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_info_cards);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ListView lvInfoCards = findViewById(R.id.lvInfoCards);

        SimpleAdapter adapter = getAdapterFromFactory();
        if(adapter != null)
            lvInfoCards.setAdapter(adapter);

        this.setTitle(R.string.activity_info_cards_title);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        ListView lvInfoCards = findViewById(R.id.lvInfoCards);

        SimpleAdapter adapter = getAdapterFromFactory();
        if(adapter != null)
            lvInfoCards.setAdapter(adapter);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            String author = getResources().getString(R.string.author_url);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(author));
            startActivity(browserIntent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_info_cards);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private SimpleAdapter getAdapterFromFactory(){
        ListViewAdaptersAbstractFactory factory = new ListViewAdaptersAbstractFactory(this);
        SimpleAdapter adapter = factory.selectAdapter(R.id.lvInfoCards);
        return adapter;
    }




}
