package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pharmacies extends AppCompatActivity {

    public static String PharmaciesWasClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies);

        ImageButton bttnSuperPharm = (ImageButton)findViewById(R.id.bttnSuperPharme);
        bttnSuperPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PharmaciesWasClicked="סופר";
                OnClickPharamacies(v);
            }
        });

        ImageButton bttnNewPharm = (ImageButton)findViewById(R.id.bttnNewPharme);
        bttnNewPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PharmaciesWasClicked="ניו";
                OnClickPharamacies(v);
            }
        });

        ImageButton bttnBetMerkahat = (ImageButton)findViewById(R.id.bttnBetMerkahat);
        bttnBetMerkahat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PharmaciesWasClicked="בית מרקחת";
                OnClickPharamacies(v);
            }
        });
    }


    public void OnClickPharamacies(View v)
    {
        Intent i = new Intent(this,MapPage.class);
        startActivity(i);
    }
}
