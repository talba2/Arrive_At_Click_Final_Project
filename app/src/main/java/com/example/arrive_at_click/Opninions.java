package com.example.arrive_at_click;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Opinion;

import java.io.File;
import java.util.ArrayList;

public class Opninions extends AppCompatActivity {

    private ArrayList<Opinion> opinionList;
    private int opinionToUpdate;
    private File database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opninions);

        ConnectionClass.DBHelper = new DatabaseHelper(this);
        database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (database.exists() == false) {
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con = new ConnectionClass();
        }

        opinionList = ConnectionClass.DBHelper.getListOpinions("*", "approved = 0");


    }

    public void OnClickDecline(View v)
    {
        String str = "update Opinion set approved = 2 where IdOpinion =" + opinionToUpdate;
        //opinionList.toArray();
    }

    public void OnClickApprove(View v)
    {
        String str = " update Opinion set approved = 1 where IdOpinion = " + opinionToUpdate ;
    }


}
