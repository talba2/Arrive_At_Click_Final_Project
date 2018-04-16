package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Context;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ImageButton bttnBank = (ImageButton)findViewById(R.id.bttnBank);
        bttnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickBanks(v);
            }
        });

        ImageButton bttnPharmacies = (ImageButton)findViewById(R.id.bttnPharmacy);
        bttnPharmacies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickPharmacies(v);
            }
        });

        ImageButton bttnPostal = (ImageButton)findViewById(R.id.bttnPostal);
        bttnPostal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickPostal(v);
            }
        });

        ImageButton bttnMunicipality = (ImageButton)findViewById(R.id.bttnMunicipality);
        bttnMunicipality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickMunicipality(v);
            }
        });

        ImageButton bttnHospitals = (ImageButton)findViewById(R.id.bttnHospital);
        bttnHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHospitals(v);
            }
        });

        ImageButton bttnHMOospitals = (ImageButton)findViewById(R.id.bttnHMO);
        bttnHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHMO(v);
            }
        });
    }

    public void OnClickBanks(View v)
    {
        Intent i = new Intent(this,Banks.class);
        startActivity(i);
    }

    public void OnClickPharmacies(View v)
    {
        Intent i = new Intent(Categories.this,Pharmacies.class);
        startActivity(i);
    }

    public void OnClickPostal(View v)
    {
        Intent i = new Intent(Categories.this,Postal_Services.class);
        startActivity(i);
    }

    public void OnClickMunicipality(View v)
    {
        Intent i = new Intent(Categories.this,Municipality.class);
        startActivity(i);
    }

    public void OnClickHospitals(View v)
    {
        Intent i = new Intent(Categories.this,Hospitals.class);
        startActivity(i);
    }

    public void OnClickHMO(View v)
    {
        Intent i = new Intent(Categories.this,HMO.class);
        startActivity(i);
    }

}