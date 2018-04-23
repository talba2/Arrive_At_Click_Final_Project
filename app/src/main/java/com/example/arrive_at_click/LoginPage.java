package com.example.arrive_at_click;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //set bttnLogin listener
        ImageButton bttnLogin = (ImageButton)findViewById(R.id.bttnLogin);
        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickLogin(v);
            }
        });
    }

    public void OnClickLogin(View v)
    {
        //need to do something
    }
}
