package com.faros.services;

import my.faros.model.Food;
import net.minidev.json.JSONObject;
import java.util.List;


/**
 * Created by guang on 2017/4/25.
 */
public interface FoodService {
    /*
    * This method is used to find a list of food entity (JSONObject) by menuId
    * */
    List<JSONObject> findFoodsByMenuId(String menuId) throws Exception;

    /*
    * This method is used to find a food entity by foodId
    * */
    Food findFoodById(String foodId) throws Exception;

    /*
    * This method is used to save a food entity
    * */
    void addFood(Food food);

    /*
    * This method is used to delete a food entity by id
    * */
    void deleteFood(String id);
}
