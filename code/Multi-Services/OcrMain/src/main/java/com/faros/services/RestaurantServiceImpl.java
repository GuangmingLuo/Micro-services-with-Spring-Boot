package com.faros.services;

import my.faros.model.Restaurant;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
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
    final String addRestaurantUrl = "http://localhost:81/api/addRestaurant";
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

    @Override
    public void addRestaurant(Restaurant restaurant) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Restaurant> request = new HttpEntity<>(restaurant);
        restTemplate.postForObject(addRestaurantUrl, request, Restaurant.class);
    }
}
