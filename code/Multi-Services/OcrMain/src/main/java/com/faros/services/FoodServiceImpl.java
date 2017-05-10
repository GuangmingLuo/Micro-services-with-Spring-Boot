package com.faros.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("FoodService")
public class FoodServiceImpl implements FoodService {

    final String foodsById = "http://localhost:83/api/food?menuId={menuId}";
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
    public void addFood(JSONObject food) {

        //foodRepository.save(food);
    }
}
