package com.example.arrive_at_click;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TextView tvTitle = (TextView) findViewById(R.id.tvDetails);
        tvTitle.setTextSize(30);

        /*TextView tvName = (TextView) findViewById(R.id.tvName);
        tvTitle.setTextSize(20);

        TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvTitle.setTextSize(20);

        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvTitle.setTextSize(20);

        TextView tvFax = (TextView) findViewById(R.id.tvFax);
        tvTitle.setTextSize(20);

        TextView tvAvailability = (TextView) findViewById(R.id.tvAvailability);
        tvAvailability.setTextSize(20);

        TextView tvAvailLevel = (TextView) findViewById(R.id.tvAvailLevel);
        tvAvailLevel.setTextSize(20);

        TextView tvAvailClientLevel = (TextView) findViewById(R.id.tvAvailClientLevel);
        tvAvailClientLevel.setTextSize(20);*/
    }
}
