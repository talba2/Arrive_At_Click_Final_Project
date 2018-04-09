package com.example.arrive_at_click;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

public class BankLeumiMap extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Marker[] placeMarkers;
    private int num;
    private MarkerOptions[] places;
    public LatLng myCurrentLoc;
    private MarkerOptions banksOptions = new MarkerOptions();
    private ArrayList<LatLng> banksPoints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_leumi_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);
        enableMyLocationIfPermitted();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);

        try {
            addBankLeumiPoints();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBankLeumiPoints() throws SQLException {
        /*LatLng lat1=new LatLng(32.063722, 34.76962);
        mMap.addMarker(new MarkerOptions().position(lat1).title("אחד העם 9"));

        LatLng lat2=new LatLng(32.075108, 34.775822);
        mMap.addMarker(new MarkerOptions().position(lat1).title("דיזינגוף 55 מגדל על"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(lat2));


        num = MainActivity.con.CountRecords("Sites", " name LIKE '%בנק לאומי%'");
        placeMarkers = new Marker[num];
        JSONObject resultObject = new JSONObject();
        JSONArray placesArray = resultObject.getJSONArray("results");
        places = new MarkerOptions[num];

        LatLng lat3=new LatLng(30.0, 35.0);
        String tmp= String.valueOf(num);
        mMap.addMarker(new MarkerOptions().position(lat3).title(tmp));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lat3));

        //loop through places
        for (int p=0; p<placesArray.length(); p++) {
            //parse each place
        }*/

        /*
        ResultSet bankLeumiTable=MainActivity.con.getTable("addSite, coorA, coorB","Sites", " name LIKE '%בנק לאומי%'");
        try {
            while (bankLeumiTable.next())
            {
                String address=bankLeumiTable.getString("addSite");
                float latitude=bankLeumiTable.getFloat("coorA");
                float longitude=bankLeumiTable.getFloat("coorB");
                LatLng lt=new LatLng(latitude,longitude);
                //banksPoints.add(lt);
                banksOptions.position(lt);
                banksOptions.title(address);
                mMap.addMarker(banksOptions);
            }
        }
        catch (SQLException e ) {
             e.printStackTrace();
         }*/
    }

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

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(32.080880, 34.780570);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }
        }
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

                    /*CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(),
                            location.getLongitude()));

                    circleOptions.radius(200);
                    circleOptions.fillColor(Color.RED);
                    circleOptions.strokeWidth(6);

                    mMap.addCircle(circleOptions);*/

                    LatLng myCurrentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(myCurrentLoc));
                    mMap.addMarker(new MarkerOptions().position(myCurrentLoc).title("My current position"));
                }
            };
}
