package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistedSearch extends AppCompatActivity {
    public static int isOrdinary=0;
    public static int isSuitable=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registed_search);

        Button bttnOrdinary = findViewById(R.id.bttnOrdinary);
        bttnOrdinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickOrdinary(v);
            }
        });

        Button bttnSuitable = findViewById(R.id.bttnSuitable);
        bttnSuitable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSuitable(v);
            }
        });
    }

    private void OnClickOrdinary(View v)
    {
        isOrdinary=1;
        isSuitable=0;
        Intent i = new Intent(this,ChooseSearchMethod.class);
        startActivity(i);
    }

    private void OnClickSuitable(View v)
    {
        isOrdinary=0;
        isSuitable=1;
        Intent i = new Intent(this,Categories.class);
        startActivity(i);
    }
}
