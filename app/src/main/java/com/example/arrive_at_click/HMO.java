package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HMO extends AppCompatActivity {
    public static String HmoWasClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmo);

        ImageButton bttnMaccabi = (ImageButton)findViewById(R.id.bttnMaccabi);
        bttnMaccabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmoWasClicked="מכבי";
                OnClickHMO(v);
            }
        });

        ImageButton bttnMeuhedet = (ImageButton)findViewById(R.id.bttnMeuhedet);
        bttnMeuhedet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmoWasClicked="מאוחדת";
                OnClickHMO(v);
            }
        });

        ImageButton bttnClalit = (ImageButton)findViewById(R.id.bttnClalit);
        bttnClalit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmoWasClicked="כללית";
                OnClickHMO(v);
            }
        });

        ImageButton bttnLeumit = (ImageButton)findViewById(R.id.bttnLeumit);
        bttnLeumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmoWasClicked="לאומית";
                OnClickHMO(v);
            }
        });
    }

    public void OnClickHMO(View v)
    {
        Intent i = new Intent(this,MapPage.class);
        startActivity(i);
    }
}
