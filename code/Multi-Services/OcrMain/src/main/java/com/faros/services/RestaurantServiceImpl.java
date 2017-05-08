package com.faros.services;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Override
    public List<JSONObject> findAll() {
        return null;
        //return restaurantRepository.findAll();
    }

    @Override
    public JSONObject findRestaurantById(int id) {
        return null;
        //return restaurantRepository.findRestaurantById(id);
    }

    @Override
    public JSONObject findRestaurantByName(String name) {
        return null;
        //return restaurantRepository.findRestaurantByName(name);
    }
}
