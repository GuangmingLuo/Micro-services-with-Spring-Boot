package com.faros.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.faros.model.Food;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("FoodService")
public class FoodServiceImpl implements FoodService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://food-api-server/api";
    @Override
    public List<JSONObject> findFoodsByMenuId(String menuId) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode foods = restTemplate.getForObject(serviceUrl+"/foods?menuId={menuId}", JsonNode.class,menuId);
        ArrayList<JSONObject> foodList = mapper.readValue(
                mapper.treeAsTokens(foods),
                new TypeReference<List<JSONObject>>(){}
        );
        return foodList;
    }

    @Override
    public Food findFoodById(String foodId) throws Exception {
        return restTemplate.getForObject(serviceUrl+"/food?foodId={foodId}", Food.class,foodId);
    }

    @Override
    public void addFood(Food food){
        HttpEntity<Food> request = new HttpEntity<>(food);
        restTemplate.postForObject(serviceUrl+"/addFood", request, Food.class);
    }
}
