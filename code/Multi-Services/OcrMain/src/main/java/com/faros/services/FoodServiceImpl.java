package com.faros.services;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("FoodService")
public class FoodServiceImpl implements FoodService {

    final String foodsById = "http://localhost:83/api/food?menuId={menuId}";
    @Override
    public List<JSONObject> findFoodsByMenuId(String menuId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(foodsById, List.class,menuId);
    }

    @Override
    public void addFood(JSONObject food) {

        //foodRepository.save(food);
    }
}
