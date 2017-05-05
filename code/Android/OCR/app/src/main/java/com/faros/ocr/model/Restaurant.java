package com.faros.ocr.model;

/**
 * Created by guang on 2017/5/5.
 */

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String introduction;

    public Restaurant(int id,String name,String address,String introduction){
        this.id=id;
        this.name=name;
        this.address=address;
        this.introduction=introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
