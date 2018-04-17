package com.example.arrive_at_click;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.maps.model.MarkerOptions;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import com.example.arrive_at_click.model.Site;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import java.io.File;

public class BankLeumiMap extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private MarkerOptions banksOptions = new MarkerOptions();
    private ArrayList<Site> bankList;
    private Spinner bankLeumiSpinner;
    private ListSiteAdapter adapter;
    private DatabaseHelper DBHelper;
    public static String itemSelected;

    //private Marker[] placeMarkers;
    //private int num;
    //private MarkerOptions[] places;
    //public LatLng myCurrentLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_leumi_map);

        Button bttnGO = (Button)findViewById(R.id.bttnGo);
        bttnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGO(v);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bankLeumiSpinner=(Spinner)findViewById(R.id.spLeumi);
        DBHelper= new DatabaseHelper(this);

        //checks exists database
        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists()==false){
            DBHelper.getReadableDatabase();
            //copy db
            if(copyDatabase(this))
                Toast.makeText(this,"copy db successfully",Toast.LENGTH_SHORT).show();
            else
            {
                Toast.makeText(this,"copy db error",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //get site list from db
        bankList  = DBHelper.getListSites();
        //init adapter
        adapter = new ListSiteAdapter(this,bankList);
        //set adapter for spinner
        //bankLeumiSpinner.setAdapter(adapter);

        int length=adapter.getCount();
        String[] bankAddress=new String[length];
        for(int i=0;i<length;++i)
            bankAddress[i]=bankList.get(i).getAddSite();

        ArrayAdapter<String> bankAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,bankAddress);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankLeumiSpinner.setAdapter(bankAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        itemSelected=parent.getSelectedItem().toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void OnClickGO(View v)
    {
        Intent i = new Intent(this,Information.class);
        startActivity(i);
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

    private boolean copyDatabase(Context context)
    {
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String OutFileName=DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(OutFileName);
            byte[]buff=new byte[1024];
            int length=0;
            while((length=inputStream.read(buff))>0)
                outputStream.write(buff,0,length);
            outputStream.flush();
            outputStream.close();
            Log.w("BankLeumiMap","DB copied");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void addBankLeumiPoints() throws SQLException {

        LatLng lt=null;

        for(int i=0;i<adapter.getCount();++i)
        {
            lt=new LatLng(bankList.get(i).getLatitude(),bankList.get(i).getLongitude());
            banksOptions.position(lt);
            banksOptions.title(bankList.get(i).getAddSite());
            mMap.addMarker(banksOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));
        }
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
