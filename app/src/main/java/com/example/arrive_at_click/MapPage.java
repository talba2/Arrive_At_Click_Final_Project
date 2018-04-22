package com.example.arrive_at_click;

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

import java.util.ArrayList;
import java.sql.SQLException;
import com.example.arrive_at_click.model.Site;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.io.File;
import java.util.Locale;

public class MapPage extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private MarkerOptions Options = new MarkerOptions();
    private ArrayList<Site> SitesList;
    private Spinner AddressSpinner;
    private ListSiteAdapter adapter;
    public static String itemSelected;
    public static String SiteName;
    public static LatLng myCurrentLoc;
    public int NumOfSites;
    public static int IdSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //set bttnGo listener
        Button bttnGO = (Button)findViewById(R.id.bttnGo);
        bttnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGO(v);
            }
        });

        //checks exists database
        ConnectionClass.DBHelper= new DatabaseHelper(this);

        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists()==false){
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con=new ConnectionClass();
            if(con.copyDatabase(this))
                Toast.makeText(this,"copy db successfully",Toast.LENGTH_SHORT).show();
            else
            {
                Toast.makeText(this,"copy db error",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        AddressSpinner=(Spinner)findViewById(R.id.AddressSpinner);

        //get bank leumi list from db
        switch(Categories.categoryName)
        {
            case "Banks":
                SiteName=Banks.BankWasClicked;
                break;
            case "Pharmacies":
                SiteName=Pharmacies.PharmaciesWasClicked;
                break;
            case "Postal":
                SiteName=Postal_Services.PostalWasClicked;
                break;
            case "Municipality":
                SiteName=Municipality.MunicipalityWasClicked;
                break;
            case "Hospitals":
                SiteName=Hospitals.HospitalsWasClicked;
                break;
            case "HMO":
                SiteName=HMO.HmoWasClicked;
                break;
            default:
                SiteName=null;
                break;
        }

        if(SiteName!=null)
        {
            SitesList  = ConnectionClass.DBHelper.getListSites("*","name LIKE '%" + SiteName + "%'");
            //init adapter
            adapter = new ListSiteAdapter(this,SitesList);

            NumOfSites=adapter.getCount();
            String[] sitesAddress=new String[NumOfSites];
            for(int i=0;i<NumOfSites;++i)
                sitesAddress[i]=SitesList.get(i).getAddSite();

            ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sitesAddress);
            SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            AddressSpinner.setAdapter(SpinnerAdapter);
        }
    }

    public void OnClickGO(View v)
    {
        itemSelected=AddressSpinner.getSelectedItem().toString();
        IdSite= getIdSite(); //get id of site that selected
        Intent i = new Intent(this,Information.class);
        startActivity(i);
    }

    public int getIdSite()
    {
        int id=-1;
        for(int i=0; i<NumOfSites; i++)
        {
            if((SitesList.get(i).getAddSite().equals(itemSelected)&&((SitesList.get(i).getName().indexOf(SiteName))>0)))
            {
                id=SitesList.get(i).getIdSite();
                i=NumOfSites;
            }
        }
        return id;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
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
            addSitesPoints();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSitesPoints() throws SQLException {

        LatLng lt=null;

        for(int i=0;i<adapter.getCount();++i)
        {
            lt=new LatLng(SitesList.get(i).getLatitude(),SitesList.get(i).getLongitude());
            Options.position(lt);
            Options.title(SitesList.get(i).getAddSite());
            mMap.addMarker(Options);
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

                    myCurrentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(myCurrentLoc));
                    mMap.addMarker(new MarkerOptions().position(myCurrentLoc).title("My current position"));
                }
            };


}

