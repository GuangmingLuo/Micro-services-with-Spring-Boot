package com.example.services;

import com.example.entities.Menu;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuService {
    public Menu findMenuByRestaurantId(int restaurantId);
}
