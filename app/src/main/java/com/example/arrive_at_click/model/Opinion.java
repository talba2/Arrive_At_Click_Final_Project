package com.example.arrive_at_click.model;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.arrive_at_click.ConnectionClass;
import com.example.arrive_at_click.MapPage;
import com.example.arrive_at_click.database.DatabaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Opinion extends AppCompatActivity {

    private String name;
    private int IdOpinion;
    private int IdSite;
    private String textOpinion;
    private int score;
    private String DateOfOpinion;

    public Opinion(String name,String dateOfOpinion,int score, String textOpinion) {
        this.name=name;
        DateOfOpinion = dateOfOpinion;
        this.score = score;
        this.textOpinion = textOpinion;
    }

    public Opinion(String name,int idOpinion, int idSite, String textOpinion, int score, String dateOfOpinion) {
        IdSite = idSite;
        this.textOpinion = textOpinion;
        this.score = score;
        DateOfOpinion = dateOfOpinion;
        this.name=name;
    }

    public String getName() { return name; }

    public int getIdOpinion() {
        return IdOpinion;
    }

    public int getIdSite() {
        return IdSite;
    }

    public String getTextOpinion() {
        return textOpinion;
    }

    public int getScore() {
        return score;
    }

    public String getDateOfOpinion() {
        return DateOfOpinion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdOpinion(int idOpinion) {
        IdOpinion = idOpinion;
    }

    public void setIdSite(int idSite) {
        IdSite = idSite;
    }

    public void setTextOpinion(String textOpinion) {
        this.textOpinion = textOpinion;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDateOfOpinion(String dateOfOpinion) {
        DateOfOpinion = dateOfOpinion;
    }
}
