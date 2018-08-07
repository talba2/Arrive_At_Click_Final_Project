package com.example.arrive_at_click;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Facilities;
import com.example.arrive_at_click.model.Site;
import java.io.File;
import java.util.ArrayList;


public class SiteToUpdate extends AppCompatActivity {

    private ArrayList<Site> siteProperties;
    private ArrayList<Facilities> arrFacilities;
    private File database;
    private EditText etOpenHours;
    private CheckBox cbRamp;
    private CheckBox cbAnimal;
    private CheckBox cbStand;
    private CheckBox cbHearing;
    private CheckBox cbRailing;
    private CheckBox cbParking;
    private CheckBox cbToilet;
    private EditText etPhone;
    private EditText etName;
    private EditText etAddress;
    private int idSite;

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

        idSite=EditSite.idSite;

        siteProperties = ConnectionClass.DBHelper.getListSites("*","Sites", "idSite=" + idSite);
        arrFacilities = ConnectionClass.DBHelper.getListFacilities("*", "IdSite=" + idSite);

        // fill the properties of the specific site
        cbRamp=(CheckBox)findViewById(R.id.cbRamp);
        cbAnimal=(CheckBox)findViewById(R.id.cbAnimal);
        cbStand=(CheckBox)findViewById(R.id.cbStand);
        cbHearing=(CheckBox)findViewById(R.id.cbHearing);
        cbRailing=(CheckBox)findViewById(R.id.cbRailing);
        cbParking=(CheckBox)findViewById(R.id.cbParking);
        cbToilet=(CheckBox)findViewById(R.id.cbToilet);

        if (arrFacilities.get(0).isHandicappedParking() == 1)
            cbParking.setChecked(true);

        if (arrFacilities.get(0).isHandicapedToillets() == 1)
            cbToilet.setChecked(true);

        if (arrFacilities.get(0).isRailing() == 1)
            cbRailing.setChecked(true);

        if (arrFacilities.get(0).isRamp() == 1)
            cbRamp.setChecked(true);

        if (arrFacilities.get(0).isHearingAid() == 1)
            cbHearing.setChecked(true);

        if (arrFacilities.get(0).isEntryToAnimal() == 1)
            cbAnimal.setChecked(true);

        if (arrFacilities.get(0).isStandForVisionImpaired() == 1)
            cbStand.setChecked(true);

        TextView tvOpenHour = (TextView) findViewById(R.id.tvOpenHours);
        if(MapPage.SiteName.equals("בית-ספר") || MapPage.SiteName.equals("גן-ילדים") || MapPage.SiteName.equals("מרחב-מוגן"))
            tvOpenHour.setText("מידע נוסף: ");
        else
            tvOpenHour.setText("שעות פתיחה: ");

        etOpenHours = (EditText) findViewById(R.id.etOpenHours);

        String hours = siteProperties.get(0).getDesSite();
        if (hours == null)
            etOpenHours.setText("נתון חסר");
        else
            etOpenHours.setText(hours);

        etAddress = (EditText) findViewById(R.id.etAddress);

        String add = siteProperties.get(0).getAddSite();
        if (add == null)
            etAddress.setText("נתון חסר");
        else
            etAddress.setText(add);

        etPhone = (EditText) findViewById(R.id.etPhone);

        String phone = siteProperties.get(0).getPhoneNum();
        if (phone == null)
            etPhone.setText("נתון חסר");
        else
            etPhone.setText(phone);

        etName = (EditText) findViewById(R.id.etName);

        String name = siteProperties.get(0).getName() +", " + siteProperties.get(0).getCategory();
        ;
        if (name == null)
            etName.setText("נתון חסר");
        else
            etName.setText(name);

    }

    public void OnClickSave(View v)
    {
        ContentValues cvDetails = new ContentValues();
        ContentValues cvFacilities = new ContentValues();
        //update site details
        int count=0;

        if(cbParking.isChecked())
        {
            count++;
            cvFacilities.put("handicappedParking",1);
        }
        else
            cvFacilities.put("handicappedParking",0);

        if(cbToilet.isChecked())
        {
            count++;
            cvFacilities.put("handicapedToillets",1);
        }
        else
            cvFacilities.put("handicapedToillets",0);

        if(cbRailing.isChecked())
        {
            count++;
            cvFacilities.put("railing",1);

        }
        else
            cvFacilities.put("railing",0);

        if(cbRamp.isChecked())
        {
            count++;
            cvFacilities.put("ramp",1);
        }
        else
            cvFacilities.put("ramp",0);

        if(cbHearing.isChecked())
        {
            count++;
            cvFacilities.put("hearingAid",1);
        }
        else
            cvFacilities.put("hearingAid",0);

        if(cbAnimal.isChecked())
        {
            count++;
            cvFacilities.put("entryToAnimals",1);
        }
        else
            cvFacilities.put("entryToAnimals",0);

        if(cbStand.isChecked())
        {
            count++;
            cvFacilities.put("standFor",1);
        }
        else
            cvFacilities.put("entryToAnimals",0);

        cvDetails.put("name",etName.getText().toString());
        cvDetails.put("addSite",etAddress.getText().toString());
        cvDetails.put("phoneNum",etPhone.getText().toString());
        cvDetails.put("desSite",etOpenHours.getText().toString());
        cvDetails.put("AccessibilityLevelByWeb",count);

        boolean siteAffected=ConnectionClass.DBHelper.update(cvDetails,"Sites","idSite=" + idSite);
        boolean facilitiesAffected=ConnectionClass.DBHelper.update(cvFacilities,"Facilities","idSite=" + idSite);

        Intent i = new Intent(this, AdminPage.class);
        startActivity(i);
    }

    public void OnClickDelete(View v)
    {
        ConnectionClass.DBHelper.delete("Facilities","idSite=" + idSite);
        ConnectionClass.DBHelper.delete("Sites","idSite=" + idSite);

        Intent i = new Intent(this, AdminPage.class);
        startActivity(i);
    }
}
