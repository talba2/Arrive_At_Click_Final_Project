package com.example.arrive_at_click;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.example.arrive_at_click.database.DatabaseHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.util.Log;

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

        TextView bttnCategory = (TextView) findViewById(R.id.bttnCategory);
        //set the font size 25
        bttnCategory.setTextSize(25);

        TextView tvSearch = (TextView) findViewById(R.id.tvSearch);
        //set the font size 20
        tvSearch.setTextSize(20);
    }

    public void OnClickCategory(View v)
    {
        Intent i = new Intent(MainActivity.this,Categories.class);
        startActivity(i);

         /*Intent i = new Intent(MainActivity.this,Information.class);
        startActivity(i);*/
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
