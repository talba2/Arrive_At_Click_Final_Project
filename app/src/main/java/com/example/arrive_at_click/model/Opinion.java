package com.example.arrive_at_click.model;

import java.util.Date;

public class Opinion {

    private int IdOpinion;
    private int IdSite;
    private String textOpinion;
    private int score;
    private Date DateOfOpinion;

    public Opinion(Date dateOfOpinion,int score, String textOpinion ) {
        DateOfOpinion = dateOfOpinion;
        this.score = score;
        this.textOpinion = textOpinion;
    }

    public Opinion(int idOpinion, int idSite, String textOpinion, int score, Date dateOfOpinion) {
        IdOpinion = idOpinion;
        IdSite = idSite;
        this.textOpinion = textOpinion;
        this.score = score;
        DateOfOpinion = dateOfOpinion;
    }


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
