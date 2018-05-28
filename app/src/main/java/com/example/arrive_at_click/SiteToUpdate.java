package com.example.arrive_at_click;

import android.content.Intent;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Facilities;
import com.example.arrive_at_click.model.Opinion;
import com.example.arrive_at_click.model.Site;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class SiteToUpdate extends AppCompatActivity {

    public static String address;
    private  int idSite;
    public static String siteName;
    public static String fieldName;
    private ArrayList<Site> siteProperties;
    private ArrayList<Facilities> arrFacilities;
    private File database;
    private EditText etOpenHours;
    private  CheckBox cbRamp;
    private CheckBox cbAnimal;
    private CheckBox cbStand;
    private CheckBox cbHearing;
    private CheckBox cbRailing;
    private CheckBox cbParking;
    private CheckBox cbToilet;
    private EditText etPhone;
    private EditText etName;
    private EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_to_update);
        ConnectionClass.DBHelper = new DatabaseHelper(this);
        database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (database.exists() == false) {
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con = new ConnectionClass();
        }
        siteProperties = ConnectionClass.DBHelper.getListSites("*","Sites", "addSite LIKE '%" + address + "%' " + "AND " + fieldName + " LIKE '%" + siteName + "%'");
        arrFacilities = ConnectionClass.DBHelper.getListFacilities("*", "addSite=" + address + "%' " + "AND " + fieldName + " LIKE '%" + siteName + "%'");

        // fill the properties of the specific site
        cbRamp=(CheckBox)findViewById(R.id.cbRamp);
        cbAnimal=(CheckBox)findViewById(R.id.cbAnimal);
        cbStand=(CheckBox)findViewById(R.id.cbStand);
        cbHearing=(CheckBox)findViewById(R.id.cbHearing);
        cbRailing=(CheckBox)findViewById(R.id.cbRailing);
        cbParking=(CheckBox)findViewById(R.id.cbParking);
        cbToilet=(CheckBox)findViewById(R.id.cbToilet);

        if (arrFacilities.get(0).isEntryToAnimal() == 1)
            cbAnimal.setChecked(true);

        if (arrFacilities.get(0).isHandicapedToillets() == 1)
            cbToilet.setChecked(true);

        if (arrFacilities.get(0).isHandicappedParking() == 1)
            cbParking.setChecked(true);

        if (arrFacilities.get(0).isHearingAid() == 1)
            cbHearing.setChecked(true);

        if (arrFacilities.get(0).isRailing() == 1)
            cbRailing.setChecked(true);

        if (arrFacilities.get(0).isRamp() == 1)
            cbRamp.setChecked(true);

        cbParking.setChecked(true);

        etOpenHours = (EditText) findViewById(R.id.etOpenHours);

        String hours = siteProperties.get(0).getDesSite();
        if (hours == null)
            etOpenHours.setText("נתון חסר");
        else
            etOpenHours.setText(hours);

        etAddress = (EditText) findViewById(R.id.etAddress);

        String add = siteProperties.get(0).getDesSite();
        if (add == null)
            etAddress.setText("נתון חסר");
        else
            etAddress.setText(add);

        etPhone = (EditText) findViewById(R.id.etPhone);

        String phone = siteProperties.get(0).getDesSite();
        if (phone == null)
            etPhone.setText("נתון חסר");
        else
            etPhone.setText(phone);


        etName = (EditText) findViewById(R.id.etName);

        String name = siteProperties.get(0).getDesSite();
        if (name == null)
            etName.setText("נתון חסר");
        else
            etName.setText(name);




    }

    public void OnClickSave(View v) {

            Intent i = new Intent(this, EditSite.class);
            startActivity(i);

    }

    public void OnClickDelete(View v) {

        String str = "delete from Sites where idSite = " + siteProperties.get(0).getIdSite();
        Intent i = new Intent(this, AdminPage.class);
        startActivity(i);

    }
}
