package com.faros.service;


import com.faros.entity.Food;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface FoodService {
    List<Food> findFoodsByMenuId(int menuId);
    Food findFoodById(long foodId);
    Food addFood(Food food);
    void deleteFood(long id);
}
