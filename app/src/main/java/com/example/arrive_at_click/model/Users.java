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
    private String idUser;
    private String userName;
    private String userPass;
    //private static int count=0;
    private String kind;
    private int percent;
    private int isWheelchair;
    private int isStick;

    public Users (String idUser,String name, String userPass , String kind, int percent, int isWheelchair, int isStick)
    {
        this.idUser=idUser;
        this.userName=name;
        this.userPass = userPass;
        this.kind=kind;
        this.percent=percent;
        this.isStick=isStick;
        this.isWheelchair=isWheelchair;
        //count ++;
        //this.idUser= count ;
    }

    public Users (String name, String userPass)
    {
        this.userName=name;
        this.userPass = userPass;
        //count ++;
        //this.idUser= count ;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getIsWheelchair() {
        return isWheelchair;
    }

    public void setIsWheelchair(int isWheelchair) {
        this.isWheelchair = isWheelchair;
    }

    public int getIsStick() {
        return isStick;
    }

    public void setIsStick(int isStick) {
        this.isStick = isStick;
    }
}
