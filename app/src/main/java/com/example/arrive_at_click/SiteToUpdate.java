package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SiteToUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_to_update);
    }

    public void OnClickSave(View v) {

            Intent i = new Intent(this, EditSite.class);
            startActivity(i);

    }

    public void OnClickDelete(View v) {

        Intent i = new Intent(this, AdminPage.class);
        startActivity(i);

    }
}
