package com.example.arrive_at_click;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;

import java.io.File;
import java.util.ArrayList;

public class EditSite extends AppCompatActivity {

    private Spinner CategorySpinner;
    private ArrayList<Site> SitesList;
    private ListSiteAdapter adapter;
    public int NumOfRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_site);


        CategorySpinner=(Spinner)findViewById(R.id.AddressSpinner);

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


        SitesList  = ConnectionClass.DBHelper.getListSites("distinct(category)","category IS NOT NULL" );

        //init adapter
        adapter = new ListSiteAdapter(this,SitesList);

        NumOfRows=adapter.getCount();
        String[] sitesAddress=new String[NumOfRows];
        for(int i=0;i<NumOfRows;++i)
            sitesAddress[i]=SitesList.get(i).getAddSite();

        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sitesAddress);
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategorySpinner.setAdapter(SpinnerAdapter);
    }
}
