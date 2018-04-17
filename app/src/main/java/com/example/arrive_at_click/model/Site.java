package com.example.arrive_at_click.model;

public class Site {
    private int idSite;
    private String name;
    private String addSite;
    private String category;
    private String phoneNum;
    //private picture
    private String desSite;
    private int accessByWeb;
    private int accessByUser;
    private float latitude;
    private float longitude;

    public Site(int idSite, String name, String addSite, String category, String phoneNum, String desSite, int accessByWeb, int accessByUser, float latitude, float longitude) {
        this.idSite = idSite;
        this.name = name;
        this.addSite = addSite;
        this.category = category;
        this.phoneNum = phoneNum;
        this.desSite = desSite;
        this.accessByWeb = accessByWeb;
        this.accessByUser = accessByUser;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddSite() {
        return addSite;
    }

    public void setAddSite(String addSite) {
        this.addSite = addSite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDesSite() {
        return desSite;
    }

    public void setDesSite(String desSite) {
        this.desSite = desSite;
    }

    public int getAccessByWeb() {
        return accessByWeb;
    }

    public void setAccessByWeb(int accessByWeb) {
        this.accessByWeb = accessByWeb;
    }

    public int getAccessByUser() {
        return accessByUser;
    }

    public void setAccessByUser(int accessByUser) {
        this.accessByUser = accessByUser;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
