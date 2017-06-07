package com.faros.services;

import my.faros.model.Restaurant;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantService {
    /*
    * This method is used to get all restaurant entities (JSONObjects)
    * */
    List<JSONObject> findAll();

    /*
    * This method is used to get a restaurant entity (JSONObject) by it's id
    * */
    JSONObject findRestaurantById(int id);

    /*
    * This method is used to get a restaurant entity (JSONObject) by it's name
    * */
    JSONObject findRestaurantByName(String name);

    /*
    * This method is used to save a restaurant entity
    * */
    void addRestaurant(Restaurant restaurant);
}
