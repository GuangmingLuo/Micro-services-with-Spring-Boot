package com.faros.services;

import my.faros.model.Food;
import net.minidev.json.JSONObject;
import java.util.List;


/**
 * Created by guang on 2017/4/25.
 */
public interface FoodService {
    List<JSONObject> findFoodsByMenuId(String menuId) throws Exception;
    Food findFoodById(String foodId) throws Exception;
    void addFood(Food food);
}
