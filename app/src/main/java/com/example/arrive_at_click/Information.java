package com.example.arrive_at_click;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.RatingBar;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Information extends AppCompatActivity {

    String siteAddress;
    private ArrayList<Site> siteList=null;
    private ArrayList<Opinion> OpinionsList=null;
    private ListOpinionAdapter OpinionAdapter;
    private ListView lvOpinion;
    private ArrayList<Facilities> FacilitiesList=null;
    private RatingBar rate;
    private TextView tvOpinion;
    private EditText etClientName;
    private EditText etOpinion;
    private  File database;
    private int AccessClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        siteAddress = MapPage.itemSelected; //keep spinner selection

        ConnectionClass.DBHelper = new DatabaseHelper(this);
        database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

        //**************** initialize lists *****************************//
        initializeLists();

        //*********************** fill fields ***************************//
        fillFields();

        //******************** add opinions ***************************//
        if(OpinionsList!=null)
            addOpinions();

        //set bttnNavigate listener
        ImageButton bttnNavigate = (ImageButton)findViewById(R.id.bttnNavigate);
        bttnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickNavigate(v);
            }
        });

        Button bttnSend = (Button)findViewById(R.id.bttnSend);
        bttnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSend(v);
            }
        });

        rate=(RatingBar)findViewById(R.id.ratingBar);
        tvOpinion=(TextView)findViewById(R.id.tvOpinion);
        etClientName=(EditText)findViewById(R.id.etClientName);
        etOpinion=(EditText)findViewById(R.id.etOpinion);
    }

    public void OnClickSend(View v)
    {
        int id=MapPage.IdSite;
        int count;

        if(isDatabaseExists())
        {
            count=ConnectionClass.DBHelper.numOfRows("Opinion",null);
            ++count;
        }
        else
            count=-1;

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String today=df.format(date);
        String name=etClientName.getText().toString();
        String opinion=etOpinion.getText().toString();

        if(name.matches(""))
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_LONG).show();
        else
        {
            String query;

            ContentValues initialValues = new ContentValues();
            initialValues.put("IdOpinion",count);
            initialValues.put("IdSite",id);
            initialValues.put("textOpinion",opinion);
            initialValues.put("score",rate.getRating());
            initialValues.put("DateOfOpinion",today);
            initialValues.put("name",name);
            ConnectionClass.DBHelper.insertValues("Opinion",initialValues);

            //update score by users
            query="SELECT SUM(score) as Total FROM Opinion";
            int sum=ConnectionClass.DBHelper.sumColumn(query);

            ContentValues cv = new ContentValues();
            cv.put("AccessibilityLevelByUsers",sum/count);
            boolean affected=ConnectionClass.DBHelper.update(cv,"Sites","idSite="+id);

            Toast.makeText(this, "thanks for sharing your opinion!!", Toast.LENGTH_LONG).show();
        }
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
        if(isDatabaseExists())
        {
            siteList = ConnectionClass.DBHelper.getListSites("*", "addSite LIKE '%" + siteAddress + "%' " + "AND name LIKE '%" + MapPage.SiteName + "%'");
            OpinionsList = ConnectionClass.DBHelper.getListOpinions("name,DateOfOpinion,score,textOpinion", "IdSite=" + MapPage.IdSite);
            FacilitiesList = ConnectionClass.DBHelper.getListFacilities("*", "IdSite=" + MapPage.IdSite);
        }
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
        else
            sitePhone.setText(phone);

        EditText siteOpenHour = (EditText) findViewById(R.id.etOpenHours);
        String hours = siteList.get(0).getDesSite();
        if (hours == null)
            siteOpenHour.setText("נתון חסר");
        else
            siteOpenHour.setText(hours);

        TextView tvTitle = (TextView) findViewById(R.id.tvDetails);
        tvTitle.setTextSize(30);

        SeekBar sbWeb = (SeekBar) findViewById(R.id.seekBarWeb);
        sbWeb.setEnabled(false);

        SeekBar sbClient = (SeekBar) findViewById(R.id.seekBarClient);
        sbClient.setEnabled(false);

        int count=0;
        int ramp=FacilitiesList.get(0).isRamp();
        CheckBox cbRamp=(CheckBox)findViewById(R.id.cbRamp);
        if(ramp==1)
        {
            cbRamp.setChecked(true);
            count++;
        }
        else
            cbRamp.setChecked(false);

        int handicappedToillets=FacilitiesList.get(0).isHandicapedToillets();
        CheckBox cbToillets=(CheckBox)findViewById(R.id.cbToilet);
        if(handicappedToillets==1)
        {
            cbToillets.setChecked(true);
            count++;
        }
        else
            cbToillets.setChecked(false);

        int handicappedParking=FacilitiesList.get(0).isHandicappedParking();
        CheckBox cbParking=(CheckBox)findViewById(R.id.cbParking);
        if(handicappedParking==1)
        {
            cbParking.setChecked(true);
            count++;
        }
        else
            cbParking.setChecked(false);

        int railing=FacilitiesList.get(0).isRailing();
        CheckBox cbRailing=(CheckBox)findViewById(R.id.cbRailing);
        if(railing==1)
        {
            cbRailing.setChecked(true);
            count++;
        }
        else
            cbRailing.setChecked(false);

        EditText siteAvailWeb =(EditText)findViewById(R.id.etAvailLevel);
        //int AccessWeb=siteList.get(0).getAccessByWeb();
        siteAvailWeb.setText(String.valueOf(count)+"/4");
        sbWeb.setProgress(count);

        EditText siteAvailClient =(EditText)findViewById(R.id.etAvailClientLevel);
        AccessClient=siteList.get(0).getAccessByUser();
        siteAvailClient.setText(String.valueOf(AccessClient)+"/4");
        sbClient.setProgress(AccessClient);

    }

    private void addOpinions()
    {
        //init adapter
        OpinionAdapter = new ListOpinionAdapter(OpinionsList,this);
        lvOpinion=(ListView)findViewById(R.id.lvOpinion);
        lvOpinion.setAdapter(OpinionAdapter);
        lvOpinion.setFastScrollEnabled(true);
    }

    private boolean isDatabaseExists()
    {
        if (database.exists() == false) {
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con = new ConnectionClass();
            if (!con.copyDatabase(this))
                return false;
        }
        return true;
    }

}
