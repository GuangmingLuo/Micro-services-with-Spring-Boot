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
    private int menuRestaurantId;
    private int menuRestaurantUserId;

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

    @Column(name = "menu_restaurant_id", nullable = false)
    @Id
    public int getMenuRestaurantId() {
        return menuRestaurantId;
    }

    public void setMenuRestaurantId(int menuRestaurantId) {
        this.menuRestaurantId = menuRestaurantId;
    }

    @Column(name = "menu_restaurant_user_id", nullable = false)
    @Id
    public int getMenuRestaurantUserId() {
        return menuRestaurantUserId;
    }

    public void setMenuRestaurantUserId(int menuRestaurantUserId) {
        this.menuRestaurantUserId = menuRestaurantUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodPK foodPK = (FoodPK) o;

        if (id != foodPK.id) return false;
        if (menuId != foodPK.menuId) return false;
        if (menuRestaurantId != foodPK.menuRestaurantId) return false;
        if (menuRestaurantUserId != foodPK.menuRestaurantUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + menuId;
        result = 31 * result + menuRestaurantId;
        result = 31 * result + menuRestaurantUserId;
        return result;
    }
}
