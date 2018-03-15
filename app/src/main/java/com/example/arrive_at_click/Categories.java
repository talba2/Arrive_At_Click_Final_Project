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

        ImageButton button = (ImageButton)findViewById(R.id.bttnBank);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickBanks(v);
            }
        });
    }

    public void OnClickBanks(View v)
    {
        final Context context = this;
        Intent i = new Intent(context,Banks.class);
        startActivity(i);
    }

    public void OnClickPharmacies(View v)
    {
        Intent i = new Intent(Categories.this,Pharmacies.class);
        startActivity(i);
    }

    public void OnClickPPostal(View v)
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


}
