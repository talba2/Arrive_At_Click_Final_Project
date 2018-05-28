package com.example.arrive_at_click.model;

public class Facilities {

    int IdSite;
    int ramp;
    int handicapedToillets;
    int handicappedParking;
    int railing;
    int hearingAid;
    int entryToAnimals;
    int standForVisionImpaired;


    public Facilities(int idSite, int ramp, int handicapedToillets, int handicappedParking, int railing, int hearingAid, int entryToAnimals, int standForVisionImpaired) {
        IdSite = idSite;
        this.ramp = ramp;
        this.handicapedToillets = handicapedToillets;
        this.handicappedParking = handicappedParking;
        this.railing = railing;
        this.hearingAid=hearingAid;
        this.entryToAnimals=entryToAnimals;
        this.standForVisionImpaired=standForVisionImpaired;
    }

    public int getIdSite() {
        return IdSite;
    }

    public void setIdSite(int idSite) {
        IdSite = idSite;
    }

    public int isRamp() {
        return ramp;
    }

    public void setRamp(int ramp) {
        this.ramp = ramp;
    }

    public int isHandicapedToillets() {
        return handicapedToillets;
    }

    public void setHandicapedToillets(int handicapedToillets) {
        this.handicapedToillets = handicapedToillets;
    }

    public int isHandicappedParking() {
        return handicappedParking;
    }

    public void setHandicappedParking(int handicappedParking) {
        this.handicappedParking = handicappedParking;
    }

    public int isRailing() {
        return railing;
    }

    public void setRailing(int railing) {
        this.railing = railing;
    }

    public int isStandForVisionImpaired() {
        return standForVisionImpaired;
    }

    public void setStandForVisionImpaired (int standForVisionImpaired) {
        this.standForVisionImpaired = standForVisionImpaired;
    }

    public int isHearingAid(){ return hearingAid;}

    public void setHearingAid(int hearingAid){
        this.hearingAid = hearingAid;
    }

    public int isEntryToAnimal() { return  entryToAnimals; }

    public void setEntryToAnimals( int entryToAnimals) {
        this.entryToAnimals = entryToAnimals;
    }
}
