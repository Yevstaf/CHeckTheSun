package com.example.yevstaf.checkthesun;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yevstaf.checkthesun.google_map_events.LoadMarkersFromDatabaseEvent;
import com.example.yevstaf.checkthesun.google_map_services.OnPlaceSelectedListener;
import com.example.yevstaf.checkthesun.interface_click_listeners.OnFabClickListeners;
import com.example.yevstaf.checkthesun.permission_managers.LocationPermissionManager;
import com.example.yevstaf.checkthesun.google_map_services.OnMapClickListener;
import com.example.yevstaf.checkthesun.google_map_services.OnMarkerClickListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener{
    private GoogleMap mMap;

    private static final int LOCATION_UPDATE_INTERVAL = 6000000;
    private static final int FASTEST_LOCATION_UPDATE_INTERVAL = 6000000;
    private final String TAG = "CheckTheSun";

    FusedLocationProviderClient mFusedLocationClient;
    LocationCallback mLocationCallback;
    PlaceAutocompleteFragment searchFragment;

    @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_drawer_map);
            Toolbar toolbar = findViewById(R.id.toolbar_map);
            setSupportActionBar(toolbar);
            setGoogleMapCallback();
            initialiseMapSearchFragment();
            setTitle(R.string.activity_map_title);

                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setImageResource(R.drawable.ic_check_circle);
                fab.setOnClickListener(new OnFabClickListeners(this,fab,mMap));


    }

    private void setGoogleMapCallback(){
        FragmentManager mFragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment =(SupportMapFragment)mFragmentManager.findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }
    private void initialiseMapSearchFragment(){
        searchFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new OnMapClickListener(this,mMap));
        mMap.setOnMarkerClickListener(new OnMarkerClickListener(this,mMap));
        searchFragment.setOnPlaceSelectedListener(new OnPlaceSelectedListener(this,mMap));
        new LoadMarkersFromDatabaseEvent(this).runEvent(googleMap);
        enableDeviceLocation();
    }
    private void enableDeviceLocation(){
        mLocationCallback = new com.example.yevstaf.checkthesun.google_map_services.LocationCallback();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setMyLocationEnabled(mMap);
    }

    private void setMyLocationEnabled(GoogleMap googleMap) {
        LocationRequest locationRequest = createConfiguredLocationRequest();

        if (buildVersionIsOK()) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) //Should be checked in here
                    == PackageManager.PERMISSION_GRANTED) {   // in order to enable location
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
                googleMap.setMyLocationEnabled(true);
            } else {
                //TODO
                checkLocationPermission();
            }
        } else {
            // TODO
            checkLocationPermission();
        }
    }

    private LocationRequest createConfiguredLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();

        locationRequest.setInterval(LOCATION_UPDATE_INTERVAL); // two minute interval
        locationRequest.setFastestInterval(FASTEST_LOCATION_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        return locationRequest;
    }
    public boolean buildVersionIsOK(){
        return android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
    public void checkLocationPermission(){
        LocationPermissionManager locationPermissionManager = new LocationPermissionManager(this);
        locationPermissionManager.checkLocationPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        LocationRequest locationRequest = new LocationRequest();
        switch (requestCode) {
            case LocationPermissionManager.PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted.
                    // Do the location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    // permission denied, Disable the
                    // functionality that depends on this permission.
                    Log.e(TAG,"No permission");
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdate();

    }
    private void stopLocationUpdate(){
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_map);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_map);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
