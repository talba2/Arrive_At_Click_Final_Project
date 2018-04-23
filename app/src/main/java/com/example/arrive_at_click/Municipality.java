package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Municipality extends AppCompatActivity {

    public static String MunicipalityWasClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        ImageButton bttnOldAge = (ImageButton)findViewById(R.id.bttnOldAge);
        bttnOldAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MunicipalityWasClicked="בית אבות";
                OnClickMunicipality(v);
            }
        });

        ImageButton bttnNursingHome = (ImageButton)findViewById(R.id.bttnNursingHome);
        bttnNursingHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MunicipalityWasClicked="מוסד סיעוד גריאטרי";
                OnClickMunicipality(v);
            }
        });

        ImageButton bttnSocialWelfare = (ImageButton)findViewById(R.id.bttnSocialWelfare);
        bttnSocialWelfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MunicipalityWasClicked="מחלקת-רווחה";
                OnClickMunicipality(v);
            }
        });

        ImageButton bttnHospital = (ImageButton)findViewById(R.id.bttnHospital);
        bttnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MunicipalityWasClicked="מרפאה כירורגית";
                OnClickMunicipality(v);
            }
        });

    }

    public void OnClickMunicipality(View v)
    {
        Intent i = new Intent(this,MapPage.class);
        startActivity(i);
    }
}
