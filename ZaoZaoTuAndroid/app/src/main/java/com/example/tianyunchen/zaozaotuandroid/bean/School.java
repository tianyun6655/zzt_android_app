package com.example.tianyunchen.zaozaotuandroid.bean;

/**
 * Created by tianyunchen on 3/13/17.
 */

public class School {
    private int sid;
    private String name;
    private String description;
    private String province;
    private String city;
    private  String area;
    private String road;
    private String locationX;
    private String locationY;


    public void setDescription(String description) {
        this.description = description;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getDescription() {
        return description;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getRoad() {
        return road;
    }

    public String getLocationX() {
        return locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
