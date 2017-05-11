package com.faros.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by guang on 2017/4/21.
 */
public class UserPK implements Serializable {
    private int id;
    private int restaurantId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPK userPK = (UserPK) o;

        if (id != userPK.id) return false;
        if (restaurantId != userPK.restaurantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + restaurantId;
        return result;
    }
}
