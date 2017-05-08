package com.faros.service;

import com.faros.entity.Restaurant;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantService {
    List<Restaurant> findAll();
    Restaurant findRestaurantById(int id);
    Restaurant findRestaurantByName(String name);
    void save(Restaurant restaurant);
}
