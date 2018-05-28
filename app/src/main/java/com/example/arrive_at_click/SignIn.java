package com.example.arrive_at_click;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Users;

import java.io.File;
import java.util.ArrayList;

public class SignIn extends AppCompatActivity {

    private File database;
    private ArrayList<Users> usersList=null;
    public static int idUser=-1;
    public static boolean isLogged=false;
    public static String userName=null;
    public static String password=null;

    private Button sign_in_button;
    private EditText etName;
    private EditText etPassword;
    public static int isStickWasClicked=0;
    public static int isWheelchairWasClicked=0;
    public static String kind=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etName = (EditText)findViewById(R.id.etName);
        etPassword = (EditText)findViewById(R.id.etPassword);

        sign_in_button =(Button)findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(v);
            }
        });

        TextView Register=(TextView)findViewById(R.id.tvRegister);
        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                OnClickRegister(v);
            }
        });

        Button bttnOrdinary = findViewById(R.id.bttnOrdinary);
        bttnOrdinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickOrdinary(v);
            }
        });
    }

    private void OnClickRegister(View v)
    {
        Intent i = new Intent(this,Registration.class);
        startActivity(i);
    }

    public void OnClickOrdinary(View v)
    {
        isLogged=false;
        Intent i = new Intent(this,ChooseSearchMethod.class);
        startActivity(i);
    }

    private void attemptLogin(View v) {

        // Store values at the time of the login attempt.
        password = etPassword.getText().toString();
        userName=etName.getText().toString();

        if(userName.matches(""))
            Toast.makeText(this, "שם משתמש הינו שדה חובה", Toast.LENGTH_LONG).show();
        // Check for a valid password, if the user entered one.
        else if(password.matches(""))
            Toast.makeText(this, "סיסמא הינו שדה חובה", Toast.LENGTH_LONG).show();
        else if(!isPasswordValid(password))
            Toast.makeText(this, "סיסמא חייבת להכיל לפחות 5 תווים", Toast.LENGTH_LONG).show();
        else
        {
            ConnectionClass.DBHelper = new DatabaseHelper(this);
            database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

            if(isDatabaseExists())
            {
                usersList = ConnectionClass.DBHelper.getListUsers("*", "userName LIKE '%" + userName + "%'");

                if(usersList.size()==0)//if the userName doesn't exist
                {
                    usersList = ConnectionClass.DBHelper.getListUsers("*", "userPass LIKE '%"+password+"%'");
                    if(usersList.size()==0)
                        Toast.makeText(this, "שם משתמש וסיסמא לא נכונים, נסה שנית.", Toast.LENGTH_LONG).show();
                    else //if password is correct
                        Toast.makeText(this, "שם משתמש שגוי, נסה שנית.", Toast.LENGTH_LONG).show();

                }
                else // if the userName exists already
                {
                    if(usersList.get(0).getUserPass().equals(password)) //if the password is correct
                    {
                        idUser=Integer.parseInt(usersList.get(0).getIdUser());
                        isWheelchairWasClicked=usersList.get(0).getIsWheelchair();
                        isStickWasClicked=usersList.get(0).getIsStick();
                        kind= usersList.get(0).getKind();

                        isLogged=true;
                        Intent i = new Intent(this,RegistedSearch.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(this, "סיסמא שגויה, נסה שנית.", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
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

}
