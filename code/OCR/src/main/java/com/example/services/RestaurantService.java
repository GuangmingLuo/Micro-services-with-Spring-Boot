package com.example.services;

import com.example.entities.Restaurant;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantService {
    List<Restaurant> findAll();
    Restaurant findRestaurantById(int id);
    Restaurant findRestaurantByName(String name);
}
