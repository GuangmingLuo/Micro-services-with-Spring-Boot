package entities;

import javax.persistence.*;

/**
 * Created by guang on 2017/4/21.
 */
@Entity
@IdClass(MenuPK.class)
public class Menu {
    private int id;
    private String name;
    private int restaurantId;
    private int restaurantUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "restaurant_id", nullable = false)
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Id
    @Column(name = "restaurant_user_id", nullable = false)
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

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (restaurantId != menu.restaurantId) return false;
        if (restaurantUserId != menu.restaurantUserId) return false;
        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + restaurantId;
        result = 31 * result + restaurantUserId;
        return result;
    }
}
