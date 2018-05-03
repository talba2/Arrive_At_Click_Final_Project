package com.example.arrive_at_click;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.sql.SQLException;
import com.example.arrive_at_click.model.Site;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.widget.Spinner;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapPage extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private MarkerOptions Options = new MarkerOptions();
    private ArrayList<Site> SitesList;
    private Spinner AddressSpinner;
    private ListSiteAdapter adapter;
    public static String itemSelected;
    public static String SiteName;
    public static int IdSite;
    public static String FieldName;
    public static LatLng myCurrentLoc;
    public int NumOfSites;
    private LatLng dest;
    private LocationManager mLocationManager;
    private Location lastKnownLocation;
    private EditText distance;
    private Polyline currentPolyline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        distance=(EditText) findViewById(R.id.etDistance);

        //set bttnGo listener
        Button bttnGO = (Button)findViewById(R.id.bttnGo);
        bttnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGO(v);
            }
        });

        AddressSpinner=(Spinner)findViewById(R.id.AddressSpinner);

        //check if the database exists
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

        //update which category waw chosen
        switch(Categories.categoryName)
        {
            case "Banks":
                SiteName=Banks.BankWasClicked;
                FieldName="name";
                break;
            case "Pharmacies":
                SiteName=Pharmacies.PharmaciesWasClicked;
                FieldName="name";
                break;
            case "Postal":
                SiteName="סניף דואר";
                FieldName="category";
                break;
            case "Municipality":
                SiteName=Municipality.MunicipalityWasClicked;
                FieldName="category";
                break;
            case "Hospitals":
                SiteName=Hospitals.HospitalsWasClicked;
                FieldName="category";
                break;
            case "HMO":
                SiteName=HMO.HmoWasClicked;
                FieldName="name";
                break;
            default:
                SiteName=null;
                break;
        }

        //update spinner list
        if(SiteName!=null)
        {
            SitesList  = ConnectionClass.DBHelper.getListSites("*",FieldName+" LIKE '%" + SiteName + "%'");
            //init adapter
            adapter = new ListSiteAdapter(this,SitesList);

            NumOfSites=adapter.getCount();
            String[] sitesAddress=new String[NumOfSites];
            for(int i=0;i<NumOfSites;++i)
                sitesAddress[i]=SitesList.get(i).getAddSite();

            ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sitesAddress);
            SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            AddressSpinner.setAdapter(SpinnerAdapter);
            AddressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                    if(currentPolyline!=null)
                        currentPolyline.remove();

                    int position=AddressSpinner.getSelectedItemPosition();
                    LatLng origin = myCurrentLoc;
                    LatLng dest = new LatLng(SitesList.get(position).getLatitude(),SitesList.get(position).getLongitude());

                    String url = getDirectionsUrl(origin, dest);
                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(url);

                    Location destLocation=new Location(LocationManager.GPS_PROVIDER);
                    destLocation.setLatitude(dest.latitude);
                    destLocation.setLongitude(dest.longitude);

                    float dis = lastKnownLocation.distanceTo(destLocation);
                    distance.setText(String.valueOf(dis/1000)+" קילומטר");
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView)
                {

                }
            });
        }

    }

    public void OnClickGO(View v)
    {
        itemSelected=AddressSpinner.getSelectedItem().toString();
        IdSite= getIdSite(); //get id of site that selected
        Intent i = new Intent(this,Information.class);
        startActivity(i);
    }
    public void OnClickMarker(String title)
    {
        itemSelected = title;
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
                if((SitesList.get(i).getAddSite().equals(itemSelected)&&((SitesList.get(i).getName().indexOf(SiteName))>0)))
                {
                    id=SitesList.get(i).getIdSite();
                    i=NumOfSites;
                }
            }
            else
            {
                if((SitesList.get(i).getAddSite().equals(itemSelected)&&((SitesList.get(i).getCategory().indexOf(SiteName))>0)))
                {
                    id=SitesList.get(i).getIdSite();
                    i=NumOfSites;
                }
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
        mMap.setMinZoomPreference(12);
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


    private void addSitesPoints() throws SQLException {

        LatLng lt=null;

        for(int i=0;i<adapter.getCount();++i)
        {
            lt=new LatLng(SitesList.get(i).getLatitude(),SitesList.get(i).getLongitude());
            if(i==0)
                dest=lt;
            Options.position(lt);
            Options.title(SitesList.get(i).getAddSite());
            mMap.addMarker(Options);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));
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

    private void enableMyLocationIfPermitted() {
        //Checking if the user has granted location permission for this app
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        }
        else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            lastKnownLocation=mLocationManager.getLastKnownLocation(mLocationManager.NETWORK_PROVIDER);
            if(lastKnownLocation!=null)
            {
                myCurrentLoc=new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude());
            }
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(32.080880, 34.780570);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(11);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {

                    mMap.setMinZoomPreference(11);
                    myCurrentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(myCurrentLoc));
                    mMap.addMarker(new MarkerOptions().position(myCurrentLoc).title("My current position"));
                }
            };


    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
           e.printStackTrace();
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>>>{

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            currentPolyline=mMap.addPolyline(lineOptions);
        }
    }

}

