package com.example.arrive_at_click;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminPage extends AppCompatActivity {

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

            private void OnClickSite(View v) {
            }
        });

        Button bttnOpnion= (Button)findViewById(R.id.bttnOpnion);
        bttnOpnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickOpnion(v);
            }

            private void OnClickOpnion(View v) {
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
}
