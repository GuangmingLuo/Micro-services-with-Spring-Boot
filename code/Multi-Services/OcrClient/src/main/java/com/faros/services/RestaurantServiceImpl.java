package com.faros.services;

import my.faros.model.Restaurant;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://restaurant-api-server/api";

    @Override
    public List<JSONObject> findAll() {
        return restTemplate.getForObject(serviceUrl+"/restaurants", List.class);
    }

    @Override
    public JSONObject findRestaurantById(int id) {
        return restTemplate.getForObject(serviceUrl+"/restaurantById?id={id}",JSONObject.class,id);
    }

    @Override
    public JSONObject findRestaurantByName(String name) {
        return restTemplate.getForObject(serviceUrl+"/restaurantByName?name={name}",JSONObject.class,name);
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        HttpEntity<Restaurant> request = new HttpEntity<>(restaurant);
        restTemplate.postForObject(serviceUrl+"/addRestaurant", request, Restaurant.class);
    }
}
