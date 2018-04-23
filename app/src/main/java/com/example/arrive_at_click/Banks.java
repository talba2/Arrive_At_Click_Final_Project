package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Banks extends AppCompatActivity {
    public static String BankWasClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        ImageButton bttnLeumi = (ImageButton)findViewById(R.id.bttnLeumi);
        bttnLeumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק לאומי";
                OnClickBank(v);
            }
        });

        ImageButton bttnPoalim = (ImageButton)findViewById(R.id.bttnPoalim);
        bttnPoalim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק הפועלים";
                OnClickBank(v);
            }
        });

        ImageButton bttnDiskont = (ImageButton)findViewById(R.id.bttnDiskont);
        bttnDiskont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק דיסקונט";
                OnClickBank(v);
            }
        });

        ImageButton bttnMarcantil = (ImageButton)findViewById(R.id.bttnMarcantil);
        bttnMarcantil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק מרכנתיל";
                OnClickBank(v);
            }
        });

        ImageButton bttnTfahot = (ImageButton)findViewById(R.id.bttnTfahot);
        bttnTfahot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק מזרחי טפחות";
                OnClickBank(v);
            }
        });

        ImageButton bttnUbank = (ImageButton)findViewById(R.id.bttnUbank);
        bttnUbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="יובנק";
                OnClickBank(v);
            }
        });

        ImageButton bttnCitibank = (ImageButton)findViewById(R.id.bttnCitiBank);
        bttnCitibank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="Citibank";
                OnClickBank(v);
            }
        });

        ImageButton bttnMasad = (ImageButton)findViewById(R.id.bttnMasad);
        bttnMasad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק מסד";
                OnClickBank(v);
            }
        });

        ImageButton bttnSalika = (ImageButton)findViewById(R.id.bttnSalika);
        bttnSalika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="סליקה בנקאי";
                OnClickBank(v);
            }
        });

        ImageButton bttnIgud = (ImageButton)findViewById(R.id.bttnIgud);
        bttnIgud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק אגוד";
                OnClickBank(v);
            }
        });

        ImageButton bttnYahav = (ImageButton)findViewById(R.id.bttnYahav);
        bttnYahav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק יהב";
                OnClickBank(v);
            }
        });

        ImageButton bttnOtzar = (ImageButton)findViewById(R.id.bttnOtzar);
        bttnOtzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק אוצר החייל";
                OnClickBank(v);
            }
        });

        ImageButton bttnHabenleumi = (ImageButton)findViewById(R.id.bttnHabenleumi);
        bttnOtzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="הבנק הבינלאומי";
                OnClickBank(v);
            }
        });

        ImageButton bttnJerusalem = (ImageButton)findViewById(R.id.bttnJerusalem);
        bttnJerusalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק ירושלים";
                OnClickBank(v);
            }
        });

        ImageButton bttnPoaleAguda = (ImageButton)findViewById(R.id.bttnPoaleAguda);
        bttnPoaleAguda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק פועלי אגודת ישראל";
                OnClickBank(v);
            }
        });

        ImageButton bttnOtomat = (ImageButton)findViewById(R.id.bttnOtomat);
        bttnOtomat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="שירותי בנק אוטומטיים";
                OnClickBank(v);
            }
        });

        ImageButton bttnDeksia = (ImageButton)findViewById(R.id.bttnDeksia);
        bttnDeksia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankWasClicked="בנק דקסיה";
                OnClickBank(v);
            }
        });
    }

    public void OnClickBank(View v)
    {
        Intent i = new Intent(this,MapPage.class);
        startActivity(i);
    }
}
