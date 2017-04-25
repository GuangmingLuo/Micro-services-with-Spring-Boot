package com.example.services;

import com.example.entities.Menu;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuService {
    public List<Menu> findMenuByRestaurantId(int restaurantId);
}
