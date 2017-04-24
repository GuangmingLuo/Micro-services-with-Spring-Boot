package com.example.services;

import com.example.entities.Restaurant;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantService {
    public List<Restaurant> findAll();
    public Restaurant findRestaurantById(int id);
}
