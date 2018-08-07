package com.example.arrive_at_click;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    public static ConnectionClass con = new ConnectionClass();
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        //set the font size 60
        tvWelcome.setTextSize(60);

        TextView tvLanguage = (TextView) findViewById(R.id.tvLanguage);
        //set the font size 30
        tvLanguage.setTextSize(30);

    }

    public void OnClickOk(View v)
    {
        Intent i = new Intent(this,SignIn.class);
        startActivity(i);
    }

    public void OnClickAdmin(View v)
    {
        Intent i = new Intent(this,LoginPage.class);
        startActivity(i);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
