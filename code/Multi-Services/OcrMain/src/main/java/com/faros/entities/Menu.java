package com.faros.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by guang on 2017/4/21.
 */
public class Menu {
    private int id;
    private String name;
    private int restaurantId;
    private List<Food> foods;

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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


    @Transient
    public List<Food> getFoods(){ return foods;}
    public void setFoods(List<Food> foods){this.foods =foods;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (restaurantId != menu.restaurantId) return false;
        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + restaurantId;
        return result;
    }
}
