package com.example.arrive_at_click;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ListView;

import com.example.arrive_at_click.adapter.ListOpinionAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Facilities;
import com.example.arrive_at_click.model.Opinion;
import com.example.arrive_at_click.model.Site;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Information extends AppCompatActivity {

    String siteAddress;
    private ArrayList<Site> siteList=null;
    private ArrayList<Opinion> OpinionsList=null;
    private ListOpinionAdapter OpinionAdapter;
    private ListView lvOpinion;
    private ArrayList<Facilities> FacilitiesList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        siteAddress = MapPage.itemSelected; //keep spinner selection


        //**************** initialize lists *****************************//
        initializeLists();

        //*********************** fill fields ***************************//
        fillFields();

        //******************** add opinions ***************************//
        addOpinions();

        //set bttnGo listener
        ImageButton bttnNavigate = (ImageButton)findViewById(R.id.bttnNavigate);
        bttnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickNavigate(v);
            }
        });

    }

    public void OnClickNavigate(View v)
    {
        Uri gmmIntentUri=Uri.parse("google.navigation:q="+siteAddress);
        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void initializeLists()
    {
        ConnectionClass.DBHelper = new DatabaseHelper(this);

        //checks exists database
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

        siteList = ConnectionClass.DBHelper.getListSites("*", "addSite LIKE '%" + siteAddress + "%' " + "AND name LIKE '%" + MapPage.SiteName + "%'");
        OpinionsList = ConnectionClass.DBHelper.getListOpinions("DateOfOpinion,score,textOpinion", "IdSite=" + MapPage.IdSite);
        FacilitiesList = ConnectionClass.DBHelper.getListFacilities("*", "IdSite=" + MapPage.IdSite);
    }

    private void fillFields()
    {
        //set values of a specific site
        EditText siteName = (EditText) findViewById(R.id.etName);
        siteName.setText(siteList.get(0).getName());

        EditText siteAdd = (EditText) findViewById(R.id.etAddress);
        siteAdd.setText(siteList.get(0).getAddSite());

        EditText sitePhone = (EditText) findViewById(R.id.etPhone);
        String phone = siteList.get(0).getPhoneNum();
        if (phone == null)
            sitePhone.setText("נתון חסר");
            //sitePhone.setTextColor(8421504); //gray color
        else
            sitePhone.setText(phone);

        //fax number??

        EditText siteOpenHour = (EditText) findViewById(R.id.etOpenHours);
        String hours = siteList.get(0).getDesSite();
        if (hours == null)
            siteOpenHour.setText("נתון חסר");
            //siteOpenHour.setTextColor(8421504); //gray color
        else
            siteOpenHour.setText(hours);

        TextView tvTitle = (TextView) findViewById(R.id.tvDetails);
        tvTitle.setTextSize(30);

        SeekBar sbWeb = (SeekBar) findViewById(R.id.seekBarWeb);
        sbWeb.setEnabled(false);

        SeekBar sbClient = (SeekBar) findViewById(R.id.seekBarClient);
        sbClient.setEnabled(false);

        EditText siteAvailWeb =(EditText)findViewById(R.id.etAvailLevel);
        int AccessWeb=siteList.get(0).getAccessByWeb();
        siteAvailWeb.setText(String.valueOf(AccessWeb)+"/4");
        sbWeb.setProgress(AccessWeb);

        EditText siteAvailClient =(EditText)findViewById(R.id.etAvailClientLevel);
        int AccessClient=siteList.get(0).getAccessByUser();
        siteAvailClient.setText(String.valueOf(AccessClient)+"/4");
        sbClient.setProgress(AccessClient);

        int ramp=FacilitiesList.get(0).isRamp();
        CheckBox cbRamp=(CheckBox)findViewById(R.id.cbRamp);
        if(ramp==1)
            cbRamp.setChecked(true);
        else
            cbRamp.setChecked(false);

        int handicappedToillets=FacilitiesList.get(0).isHandicapedToillets();
        CheckBox cbToillets=(CheckBox)findViewById(R.id.cbToilet);
        if(handicappedToillets==1)
            cbToillets.setChecked(true);
        else
            cbToillets.setChecked(false);

        int handicappedParking=FacilitiesList.get(0).isHandicappedParking();
        CheckBox cbParking=(CheckBox)findViewById(R.id.cbParking);
        if(handicappedParking==1)
            cbParking.setChecked(true);
        else
            cbParking.setChecked(false);

        int railing=FacilitiesList.get(0).isRailing();
        CheckBox cbRailing=(CheckBox)findViewById(R.id.cbRailing);
        if(railing==1)
            cbRailing.setChecked(true);
        else
            cbRailing.setChecked(false);
    }

    private void addOpinions()
    {
        //init adapter
        OpinionAdapter = new ListOpinionAdapter(this,OpinionsList);
        lvOpinion=(ListView)findViewById(R.id.lvOpinion);
        lvOpinion.setAdapter(OpinionAdapter);
        /*
            TextView tvEmpty=(TextView)findViewById(R.id.empty);
            tvEmpty.setText("אין עדיין חוות דעת לאתר זה.");
        */
    }

}
