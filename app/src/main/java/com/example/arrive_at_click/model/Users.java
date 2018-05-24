package com.example.arrive_at_click.model;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.arrive_at_click.ConnectionClass;
import com.example.arrive_at_click.MapPage;
import com.example.arrive_at_click.database.DatabaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Users {
    private int idUser;
    private String userName;
    private String mail;
    private String userPass;
    private static int count=0;

    public Users (int idUser,String name, String userPass , String mail)
    {
        this.idUser=idUser;
        this.userName=name;
        this.userPass = userPass;
        this.mail = mail;
        //count ++;
        //this.idUser= count ;
    }

    public Users (String name, String userPass)
    {
        this.userName=name;
        this.userPass = userPass;
        this.mail = "";
        //count ++;
        //this.idUser= count ;
    }

    public String getUserName() {return this.userName;}

    public String getUserPass() { return this.userPass;}

    public void setUserPass(String userPass){ this.userPass=userPass;}

    public String getMail(){return this.mail;}

    public void setMail(String mail){ this.mail=mail;}

    public int getIdUser() { return this.idUser;}


}
