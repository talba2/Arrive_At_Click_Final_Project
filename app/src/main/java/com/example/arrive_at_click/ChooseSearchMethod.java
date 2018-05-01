package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseSearchMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_search_method);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        //set the font size 23
        tvTitle.setTextSize(23);

        TextView bttnCategory = (TextView) findViewById(R.id.bttnCategory);
        //set the font size 25
        bttnCategory.setTextSize(25);

        TextView tvSearch = (TextView) findViewById(R.id.tvSearch);
        //set the font size 20
        tvSearch.setTextSize(20);
    }

    public void OnClickCategory(View v)
    {
        Intent i = new Intent(ChooseSearchMethod.this,Categories.class);
        startActivity(i);
    }

    public void OnClickSearch(View v)
    {
        EditText txtVal = (EditText)findViewById(R.id.etSearch);
        ResultsOfSearch.searchValue = txtVal.getText().toString();

        Intent i = new Intent(this,ResultsOfSearch.class);

        startActivity(i);

    }
}
