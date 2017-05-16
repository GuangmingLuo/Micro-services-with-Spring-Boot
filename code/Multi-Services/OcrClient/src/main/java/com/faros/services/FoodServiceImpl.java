package com.faros.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.faros.model.Food;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final String foodsById = "http://localhost:83/api/food?menuId={menuId}";
    final String addFoodUrl = "http://localhost:83/api/addFood";
    private static final Logger log = LoggerFactory.getLogger(FoodServiceImpl.class);
    @Override
    public List<JSONObject> findFoodsByMenuId(String menuId) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode menus = restTemplate.getForObject(foodsById, JsonNode.class,menuId);
        ArrayList<JSONObject> foodList = mapper.readValue(
                mapper.treeAsTokens(menus),
                new TypeReference<List<JSONObject>>(){}
        );
        return foodList;
    }

    @Override
    public void addFood(Food food){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Food> request = new HttpEntity<>(food);
        restTemplate.postForObject(addFoodUrl, request, Food.class);
    }
}
