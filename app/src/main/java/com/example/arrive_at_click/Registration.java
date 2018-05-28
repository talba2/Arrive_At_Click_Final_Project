package com.example.arrive_at_click;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.text.TextUtils;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;

import org.w3c.dom.Text;

import java.io.File;

public class Registration extends AppCompatActivity {

    private Spinner disabilitySp;
    private CheckBox cbStick;
    private CheckBox cbWheelchair;
    private TextView tvAssistent;
    private EditText etPercentage;
    private EditText etPassword;
    private EditText etName;
    private File database;
    public static String UserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etPercentage = (EditText)findViewById(R.id.etPercentage);
        etPercentage.setText("");
        etPercentage.setFilters(new InputFilter[]{ new MinMaxFilter("1", "100")});

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setTextSize(30);

        cbStick = (CheckBox)findViewById(R.id.cbStick);
        cbStick.setVisibility(View.INVISIBLE);
        cbWheelchair = (CheckBox)findViewById(R.id.cbWheelchair);
        cbWheelchair.setVisibility(View.INVISIBLE);

        tvAssistent = (TextView)findViewById(R.id.tvAssistent);

        Button bttnDone = (Button)findViewById(R.id.bttnDoneReg);
        bttnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickDone(v);
            }
        });

        etName = (EditText)findViewById(R.id.etName);
        etName.setText("");
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword.setText("");

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
                        cbStick.setChecked(false);
                        cbWheelchair.setChecked(false);
                        break;
                    case "לקוי שמיעה":
                        cbStick.setVisibility(View.INVISIBLE);
                        cbWheelchair.setVisibility(View.VISIBLE);
                        tvAssistent.setVisibility(View.VISIBLE);
                        cbWheelchair.setText("מכשיר שמיעה");
                        cbWheelchair.setChecked(false);
                        break;
                    case "לקוי ראייה":
                        cbStick.setVisibility(View.VISIBLE);
                        cbWheelchair.setVisibility(View.VISIBLE);
                        tvAssistent.setVisibility(View.VISIBLE);
                        cbWheelchair.setText("כלב נחייה");
                        cbStick.setText("מקל נחייה");
                        cbStick.setChecked(false);
                        cbWheelchair.setChecked(false);
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
        ConnectionClass.DBHelper = new DatabaseHelper(this);
        database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(isDatabaseExists())
        {
            int numOfUsers=ConnectionClass.DBHelper.numOfRows("Users",null); //get num of users
            SignIn.idUser=numOfUsers; //starts from 0

            if(etName.getText().toString().matches(""))
                Toast.makeText(Registration.this, "שם משתמש הינו שדה חובה", Toast.LENGTH_LONG).show();
            else if(etPassword.getText().toString().matches(""))
                Toast.makeText(Registration.this, "סיסמא הינו שדה חובה", Toast.LENGTH_LONG).show();
            else if(!isPasswordValid(etPassword.getText().toString()))
                Toast.makeText(Registration.this, "סיסמא חייבת להכיל לפחות 5 תווים", Toast.LENGTH_LONG).show();
            else if((ConnectionClass.DBHelper.getListUsers("*", "userName LIKE '%" + etName.getText().toString() + "%'")).size()==1)
                Toast.makeText(Registration.this, "שם משתמש כבר קיים", Toast.LENGTH_LONG).show();
            else
            {
                switch(disabilitySp.getSelectedItem().toString()) {
                    case "בחר לקות...":
                        Toast.makeText(Registration.this, "לא בחרת סוג לקות, אנא נסה שנית", Toast.LENGTH_LONG).show();
                        break;
                    case "נכה":
                        if(etPercentage.getText().toString().matches(""))
                            Toast.makeText(Registration.this, "לא בחרת אחוז נכות, אנא נסה שנית", Toast.LENGTH_LONG).show();
                        else if (!cbStick.isChecked() && !cbWheelchair.isChecked())
                            Toast.makeText(Registration.this, "לא בחרת עזרים, אנא נסה שנית", Toast.LENGTH_LONG).show();
                        else
                        {
                            if (cbStick.isChecked() && !cbWheelchair.isChecked())
                                SignIn.isStickWasClicked=1;
                            else if(!cbStick.isChecked() && cbWheelchair.isChecked())
                                SignIn.isWheelchairWasClicked=1;
                            else
                            {
                                SignIn.isStickWasClicked=1;
                                SignIn.isWheelchairWasClicked=1;
                            }

                            SignIn.kind="נכה";

                            ContentValues initialValues = new ContentValues();
                            initialValues.put("idUser",String.valueOf(numOfUsers));
                            initialValues.put("userName",etName.getText().toString());
                            initialValues.put("userPass",etPassword.getText().toString());
                            initialValues.put("kind","נכה");
                            initialValues.put("precent",etPercentage.getText().toString());
                            initialValues.put("wheelchair",SignIn.isWheelchairWasClicked);
                            initialValues.put("walkingStick",SignIn.isStickWasClicked);

                            ConnectionClass.DBHelper.insertValues("Users",initialValues);

                            UserName=etName.getText().toString();
                            SignIn.isLogged=true;
                            Intent i = new Intent(this,RegistedSearch.class);
                            startActivity(i);
                        }
                        break;
                    case "לקוי שמיעה":
                        if(etPercentage.getText().toString().matches(""))
                            Toast.makeText(Registration.this, "לא בחרת אחוז נכות, אנא נסה שנית", Toast.LENGTH_LONG).show();
                        else
                        {
                            if(cbWheelchair.isChecked())
                                SignIn.isWheelchairWasClicked=1;
                            else
                                SignIn.isWheelchairWasClicked=0;

                            ContentValues initialValues = new ContentValues();
                            initialValues.put("idUser",numOfUsers);
                            initialValues.put("userName",etName.getText().toString());
                            initialValues.put("userPass",etPassword.getText().toString());
                            initialValues.put("kind","לקוי שמיעה");
                            initialValues.put("precent",etPercentage.getText().toString());
                            initialValues.put("wheelchair",SignIn.isWheelchairWasClicked);
                            initialValues.put("walkingStick",-1);

                            ConnectionClass.DBHelper.insertValues("Users",initialValues);

                            SignIn.isLogged=true;
                            SignIn.kind="לקוי שמיעה";
                            Intent i = new Intent(this,RegistedSearch.class);
                            startActivity(i);
                        }
                        break;
                    case "לקוי ראייה":
                        if(etPercentage.getText().toString().equals(""))
                            Toast.makeText(Registration.this, "לא בחרת אחוז נכות, אנא נסה שנית", Toast.LENGTH_LONG).show();
                        else if(!cbStick.isChecked()&& !cbWheelchair.isChecked())
                            Toast.makeText(Registration.this, "לא בחרת עזרים, אנא נסה שנית", Toast.LENGTH_LONG).show();
                        else
                        {
                            if (cbStick.isChecked() && !cbWheelchair.isChecked())
                                SignIn.isStickWasClicked=1;
                            else if(!cbStick.isChecked() && cbWheelchair.isChecked())
                                SignIn.isWheelchairWasClicked=1;
                            else
                            {
                                SignIn.isStickWasClicked=1;
                                SignIn.isWheelchairWasClicked=1;
                            }

                            ContentValues initialValues = new ContentValues();
                            initialValues.put("idUser",numOfUsers);
                            initialValues.put("userName",etName.getText().toString());
                            initialValues.put("userPass",etPassword.getText().toString());
                            initialValues.put("kind","לקוי ראייה");
                            initialValues.put("precent",etPercentage.getText().toString());
                            initialValues.put("wheelchair",SignIn.isWheelchairWasClicked);
                            initialValues.put("walkingStick",SignIn.isStickWasClicked);

                            ConnectionClass.DBHelper.insertValues("Users",initialValues);

                            SignIn.kind="לקוי ראייה";
                            SignIn.isLogged=true;
                            Intent i = new Intent(this,RegistedSearch.class);
                            startActivity(i);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private boolean isDatabaseExists()
    {
        if (database.exists() == false) {
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con = new ConnectionClass();
            if (!con.copyDatabase(this))
                return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public class MinMaxFilter implements InputFilter {

        private int mIntMin, mIntMax;

        public MinMaxFilter(int minValue, int maxValue) {
            this.mIntMin = minValue;
            this.mIntMax = maxValue;
        }

        public MinMaxFilter(String minValue, String maxValue) {
            this.mIntMin = Integer.parseInt(minValue);
            this.mIntMax = Integer.parseInt(maxValue);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(mIntMin, mIntMax, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
