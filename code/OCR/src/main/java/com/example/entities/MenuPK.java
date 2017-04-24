package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by guang on 2017/4/21.
 */
public class MenuPK implements Serializable {
    private int id;
    private int restaurantId;
    private int restaurantUserId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "restaurant_id", nullable = false)
    @Id
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Column(name = "restaurant_user_id", nullable = false)
    @Id
    public int getRestaurantUserId() {
        return restaurantUserId;
    }

    public void setRestaurantUserId(int restaurantUserId) {
        this.restaurantUserId = restaurantUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuPK menuPK = (MenuPK) o;

        if (id != menuPK.id) return false;
        if (restaurantId != menuPK.restaurantId) return false;
        if (restaurantUserId != menuPK.restaurantUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + restaurantId;
        result = 31 * result + restaurantUserId;
        return result;
    }
}
