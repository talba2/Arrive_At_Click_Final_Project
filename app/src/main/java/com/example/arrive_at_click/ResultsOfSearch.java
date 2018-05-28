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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultsOfSearch extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static String searchValue;
    private ArrayList<Site> sitesList;
    private ListSiteAdapter adapter;
    private MarkerOptions Options = new MarkerOptions();
    private Spinner resultSpinner;
    public int NumOfSites;
    public static String itemSelected;
    public static String SiteName;
    public static int IdSite;
    public static String FieldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_of_search);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //set bttnGo listener
        Button bttnGO = (Button) findViewById(R.id.bttnGo);
        bttnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGO(v);
            }
        });

        resultSpinner=(Spinner)findViewById(R.id.AddressSpinner);

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

        sitesList = ConnectionClass.DBHelper.getListSites("*", "Sites","addSite LIKE '%" + searchValue + "%' OR name LIKE '%" + searchValue + "%' OR category LIKE '%"+ searchValue + "%'" );

        //init adapter
        adapter = new ListSiteAdapter(this,sitesList);

        NumOfSites=adapter.getCount();
        String[] sitesName=new String[NumOfSites];
        for(int i=0;i<NumOfSites;++i)
            sitesName[i]=sitesList.get(i).getName();

        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sitesName);
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultSpinner.setAdapter(SpinnerAdapter);
    }
    private void OnClickGO(View v) {
        itemSelected=resultSpinner.getSelectedItem().toString();
        IdSite= getIdSite(); //get id of site that selected
        Intent i = new Intent(this,Information.class);
        startActivity(i);
    }

    public int getIdSite()
    {
        int id=-1;
        for(int i=0; i<NumOfSites; i++)
        {
            if(FieldName.equals("name"))
            {
                if((sitesList.get(i).getAddSite().equals(itemSelected)&&((sitesList.get(i).getName().indexOf(SiteName))>0)))
                {
                    id=sitesList.get(i).getIdSite();
                    i=NumOfSites;
                }
            }
            else
            {
                if((sitesList.get(i).getAddSite().equals(itemSelected)&&((sitesList.get(i).getCategory().indexOf(SiteName))>0)))
                {
                    id=sitesList.get(i).getIdSite();
                    i=NumOfSites;
                }
            }

        }
        return id;
    }

    public void OnClickMarker(String title)
    {
        itemSelected = title;
        IdSite= getIdSite(); //get id of site that selected
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
        mMap.setMinZoomPreference(11);
        mMap.setOnInfoWindowClickListener(this);

        try {
            addSitesPoints();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        this.OnClickMarker(marker.getTitle());
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

    private void addSitesPoints() throws SQLException {

        LatLng lt=null;

        for(int i=0;i<adapter.getCount();++i)
        {
            lt=new LatLng(sitesList.get(i).getLatitude(),sitesList.get(i).getLongitude());
            Options.position(lt);
            Options.title(sitesList.get(i).getAddSite());
            mMap.addMarker(Options);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));
    }
}
