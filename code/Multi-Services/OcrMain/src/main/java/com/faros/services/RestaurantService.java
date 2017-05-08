package com.faros.services;

import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantService {
    List<JSONObject> findAll();
    JSONObject findRestaurantById(int id);
    JSONObject findRestaurantByName(String name);
}
