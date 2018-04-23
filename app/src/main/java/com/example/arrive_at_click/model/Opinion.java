package com.example.arrive_at_click.model;

import java.util.Date;

public class Opinion {

    private String name;
    private int IdOpinion;
    private int IdSite;
    private String textOpinion;
    private int score;
    private Date DateOfOpinion;

    public Opinion(String name,Date dateOfOpinion,int score, String textOpinion ) {
        this.name=name;
        DateOfOpinion = dateOfOpinion;
        this.score = score;
        this.textOpinion = textOpinion;
    }

    public Opinion(String name,int idOpinion, int idSite, String textOpinion, int score, Date dateOfOpinion) {
        IdOpinion = idOpinion;
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

    public Date getDateOfOpinion() {
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

    public void setDateOfOpinion(Date dateOfOpinion) {
        DateOfOpinion = dateOfOpinion;
    }
}
