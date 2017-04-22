package com.example.tianyunchen.zaozaotuandroid.bean;

/**
 * Created by tianyunchen on 3/8/17.
 */

public class Catogry {
    private int caid;
    private String name;
    private String desc;
    private String pre_image;

    public String getDesc() {
        return desc;
    }

    public String getPre_image() {
        return pre_image;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPre_image(String pre_image) {
        this.pre_image = pre_image;
    }

    public void setCaid(int caid) {
        this.caid = caid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaid() {
        return caid;
    }

    public String getName() {
        return name;
    }
}
