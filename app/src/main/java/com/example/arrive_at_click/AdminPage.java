package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;

import java.io.File;
import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {



    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);


        Button bttnSite= (Button)findViewById(R.id.bttnSite);
        bttnSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSite(v);
            }
        });

        Button bttnOpnion= (Button)findViewById(R.id.bttnOpnion);
        bttnOpnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickOpnion(v);
            }

        });

        Button bttnMap= (Button)findViewById(R.id.bttnMap);
        bttnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickMap(v);
            }

            private void OnClickMap(View v) {
            }
        });

    }

    private void OnClickSite(View v) {

        Intent i = new Intent(this, EditSite.class);
        startActivity(i);
    }

    private void OnClickOpnion(View v) {
        Intent i = new Intent(this, Opninions.class);
        startActivity(i);

    }

    private void OnClickMap(View v) {
        Intent i = new Intent(this, SlopeMap.class);
        startActivity(i);

    }

}
