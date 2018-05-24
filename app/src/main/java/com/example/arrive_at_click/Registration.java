package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;

import org.w3c.dom.Text;

import java.io.File;

public class Registration extends AppCompatActivity {

    private Spinner disabilitySp;
    private CheckBox cbStick;
    private CheckBox cbWheelchair;
    private TextView tvAssistent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        cbStick = (CheckBox)findViewById(R.id.cbStick);
        cbWheelchair = (CheckBox)findViewById(R.id.cbWheelchair);
        tvAssistent = (TextView)findViewById(R.id.tvAssistent);

        Button bttnDone = (Button)findViewById(R.id.bttnDoneReg);
        bttnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickDone(v);
            }
        });

        disabilitySp = (Spinner)findViewById(R.id.disabilitySpinner);
        disabilitySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapt, View v, int i, long lng) {
                switch(disabilitySp.getSelectedItem().toString())
                {
                    case "בחר לקות...":
                        cbStick.setVisibility(View.INVISIBLE);
                        cbWheelchair.setVisibility(View.INVISIBLE);
                        break;
                    case "נכה":
                        cbStick.setVisibility(View.VISIBLE);
                        cbWheelchair.setVisibility(View.VISIBLE);
                        tvAssistent.setVisibility(View.VISIBLE);
                        break;
                    case "לקוי שמיעה":
                        cbStick.setVisibility(View.INVISIBLE);
                        cbWheelchair.setVisibility(View.INVISIBLE);
                        tvAssistent.setVisibility(View.INVISIBLE);
                        break;
                    case "לקוי ראייה":
                        cbStick.setVisibility(View.VISIBLE);
                        cbWheelchair.setVisibility(View.VISIBLE);
                        tvAssistent.setVisibility(View.VISIBLE);
                        cbWheelchair.setText("כלב נחייה");
                        cbStick.setText("מקל נחייה");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void OnClickDone(View v)
    {
        switch(disabilitySp.getSelectedItem().toString()) {
            case "בחר לקות...":
                Toast.makeText(Registration.this, "לא בחרת סוג לקות, אנא נסה שנית", Toast.LENGTH_LONG).show();
                break;
            case "נכה":
                if (!cbStick.isChecked() && !cbWheelchair.isChecked())
                    Toast.makeText(Registration.this, "לא בחרת עזרים, אנא נסה שנית", Toast.LENGTH_LONG).show();
                else
                {
                    LoginActivity.isLogged=true;
                    Intent i = new Intent(this,ChooseSearchMethod.class);
                    startActivity(i);
                }
                break;
            case "לקוי שמיעה":
                break;
            case "לקוי ראייה":
                if(!cbStick.isChecked()&& !cbWheelchair.isChecked())
                    Toast.makeText(Registration.this, "לא בחרת עזרים, אנא נסה שנית", Toast.LENGTH_LONG).show();
                else
                {
                    LoginActivity.isLogged=true;
                    Intent i = new Intent(this,ChooseSearchMethod.class);
                    startActivity(i);
                }
                break;
            default:
                break;
        }

    }
}
