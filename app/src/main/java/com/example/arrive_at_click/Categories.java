package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Map;

public class Categories extends AppCompatActivity {

    public static String categoryName=null;

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

        ImageButton bttnHMO = (ImageButton)findViewById(R.id.bttnHMO);
        bttnHMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHMO(v);
            }
        });

        ImageButton bttnSchool = (ImageButton)findViewById(R.id.bttnSchool);
        bttnSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSchool(v);
            }
        });

        ImageButton bttnKindergarten = (ImageButton)findViewById(R.id.bttnKindergarten);
        bttnKindergarten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickKindergarten(v);
            }
        });

        ImageButton bttnMamad = (ImageButton)findViewById(R.id.bttnMamad);
        bttnMamad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickMamad(v);
            }
        });
    }

    public void OnClickBanks(View v)
    {
        categoryName="Banks";
        Intent i = new Intent(this,Banks.class);
        startActivity(i);
    }

    public void OnClickPharmacies(View v)
    {
        categoryName="Pharmacies";
        Intent i = new Intent(Categories.this,Pharmacies.class);
        startActivity(i);
    }

    public void OnClickPostal(View v)
    {
        categoryName="Postal";
        Intent i = new Intent(Categories.this,MapPage.class);
        startActivity(i);
    }

    public void OnClickMunicipality(View v)
    {
        categoryName="Municipality";
        Intent i = new Intent(Categories.this,Municipality.class);
        startActivity(i);
    }

    public void OnClickHospitals(View v)
    {
        categoryName="Hospitals";
        Intent i = new Intent(Categories.this,MapPage.class);
        startActivity(i);
    }

    public void OnClickHMO(View v)
    {
        categoryName="HMO";
        Intent i = new Intent(Categories.this,HMO.class);
        startActivity(i);
    }

    public void OnClickSchool(View v)
    {
        categoryName="School";
        Intent i = new Intent(Categories.this,MapPage.class);
        startActivity(i);
    }

    public void OnClickKindergarten(View v)
    {
        categoryName="Kindergarten";
        Intent i = new Intent(Categories.this,MapPage.class);
        startActivity(i);
    }

    public void OnClickMamad(View v)
    {
        categoryName="Mamad";
        Intent i = new Intent(Categories.this,MapPage.class);
        startActivity(i);
    }

}
