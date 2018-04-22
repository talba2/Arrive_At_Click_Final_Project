package com.example.arrive_at_click;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;

public class searchResults extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static String searchvalue;
    private ArrayList<Site> siteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        /*
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //set bttnGo listener
        Button bttnGO = (Button) findViewById(R.id.bttnGo);
        bttnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickGo(v);
            }
        });

        //checks exists database
        ConnectionClass.DBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (database.exists() == false) {
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con = new ConnectionClass();
            if (con.copyDatabase(this))
                Toast.makeText(this, "copy db successfully", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(this, "copy db error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        siteList = ConnectionClass.DBHelper.getListSites("*", "addSite LIKE '%" + searchvalue + "%' OR name LIKE '%" + searchvalue + "%' OR category LIKE '%"+ searchvalue + "%'" );
*/
        }
    private void OnClickGO(View v) {
        //itemSelected=AddressSpinner.getSelectedItem().toString();
        Intent i = new Intent(this,Information.class);
        startActivity(i);
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(15);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {

                    mMap.setMinZoomPreference(12);

                    LatLng myCurrentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(myCurrentLoc));
                    mMap.addMarker(new MarkerOptions().position(myCurrentLoc).title("My current position"));
                }
            };
    private void enableMyLocationIfPermitted() {
        //Checking if the user has granted location permission for this app
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        }
        else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }
        @Override
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;

            mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
            mMap.setOnMyLocationClickListener(onMyLocationClickListener);
            enableMyLocationIfPermitted();

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMinZoomPreference(15);

        }
}