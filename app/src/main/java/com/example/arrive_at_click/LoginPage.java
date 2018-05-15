package com.example.arrive_at_click;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

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

        EditText pass = (EditText)findViewById(R.id.etPass);
        AdminPage.count++;
        if (pass.equals("AdminOnly"))
        {
            Intent i = new Intent(this,AdminPage.class);
            startActivity(i);
        }

        else if (AdminPage.count <3 )
        {

            PopupWindow po = new PopupWindow();
            
            /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder();

            final EditText et = new EditText(context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(et);

            // set dialog message
            alertDialogBuilder.setCancelable(false).setPositiveButton("Error! Wrong password", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();*/
        }
        else {
        //Exit App
        }

    }
}
