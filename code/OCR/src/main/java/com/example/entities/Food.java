package com.example.entities;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by guang on 2017/4/21.
 */
@Entity
@Table(name = "food", schema = "mydb")
@IdClass(FoodPK.class)
public class Food {
    private int id;
    private String name;
    private Integer discount;
    private byte[] image;
    private int menuId;
    private int menuRestaurantId;
    private int menuRestaurantUserId;

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

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "image", nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Id
    @Column(name = "menu_id", nullable = false)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Id
    @Column(name = "menu_restaurant_id", nullable = false)
    public int getMenuRestaurantId() {
        return menuRestaurantId;
    }

    public void setMenuRestaurantId(int menuRestaurantId) {
        this.menuRestaurantId = menuRestaurantId;
    }

    @Id
    @Column(name = "menu_restaurant_user_id", nullable = false)
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

        Food food = (Food) o;

        if (id != food.id) return false;
        if (menuId != food.menuId) return false;
        if (menuRestaurantId != food.menuRestaurantId) return false;
        if (menuRestaurantUserId != food.menuRestaurantUserId) return false;
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
        result = 31 * result + menuRestaurantId;
        result = 31 * result + menuRestaurantUserId;
        return result;
    }
}
