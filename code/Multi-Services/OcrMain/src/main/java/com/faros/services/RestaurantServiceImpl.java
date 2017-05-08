package com.faros.services;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    final String restaurants = "http://localhost:81/api/restaurants";
    final String restaurantById = "http://localhost:81/api/restaurantById?id={id}";
    final String restaurantByName = "http://localhost:81/api/restaurantByName?name={name}";
    @Override
    public List<JSONObject> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(restaurants, List.class);
    }

    @Override
    public JSONObject findRestaurantById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(restaurantById,JSONObject.class,id);
    }

    @Override
    public JSONObject findRestaurantByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(restaurantByName,JSONObject.class,name);
    }
}
