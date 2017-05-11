package com.faros.services;

import com.faros.entities.Restaurant;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantService {
    List<JSONObject> findAll();
    JSONObject findRestaurantById(int id);
    JSONObject findRestaurantByName(String name);
    void addRestaurant(Restaurant restaurant);
}
