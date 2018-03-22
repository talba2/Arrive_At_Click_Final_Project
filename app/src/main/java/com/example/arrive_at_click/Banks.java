package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Banks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        ImageButton bttnBankLeumi = (ImageButton)findViewById(R.id.bttnLeumi);
        bttnBankLeumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickLeumi(v);
            }
        });
    }

    public void OnClickLeumi(View v)
    {
        Intent i = new Intent(this,BankLeumiMap.class);
        startActivity(i);
    }
}
