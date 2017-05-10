package com.faros.services;

import com.faros.entities.Food;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface FoodService {
    List<JSONObject> findFoodsByMenuId(String menuId) throws IOException, Exception;
    void addFood(Food food);
}
