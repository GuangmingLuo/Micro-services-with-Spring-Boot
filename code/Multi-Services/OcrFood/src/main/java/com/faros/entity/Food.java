package com.faros.entity;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

/**
 * Created by guang on 2017/4/21.
 */

public class Food {
    private int id;
    private float price;
    private String name;
    private Integer discount;
    private byte[] image;
    private int menuId;

    @Id
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

    public float getPrice(){return price;}
    public void setPrice(float price){this.price=price;}

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != food.id) return false;
        if (menuId != food.menuId) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (discount != null ? !discount.equals(food.discount) : food.discount != null) return false;
        if (!Arrays.equals(image, food.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + menuId;
        return result;
    }
}
