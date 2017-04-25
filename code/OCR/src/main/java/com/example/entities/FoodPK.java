package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by guang on 2017/4/21.
 */
public class FoodPK implements Serializable {
    private int id;
    private int menuId;
    private float price;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "menu_id", nullable = false)
    @Id
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Column(name = "price", nullable = false)
    public float getPrice(){return price;}
    public void setPrice(float price){this.price=price;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodPK foodPK = (FoodPK) o;

        if (id != foodPK.id) return false;
        if (menuId != foodPK.menuId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + menuId;
        return result;
    }
}
