package com.faros.ocr.model;

/**
 * Created by guang on 2017/5/4.
 */

public class Food {
    private String name;
    private float price;
    private float discount;
    public Food(String name,float price,float discount){
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
