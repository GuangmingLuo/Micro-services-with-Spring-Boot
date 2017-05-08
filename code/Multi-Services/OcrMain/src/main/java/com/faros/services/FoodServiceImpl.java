package com.faros.services;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("FoodService")
public class FoodServiceImpl implements FoodService {

    @Override
    public List<JSONObject> findFoodsByMenuId(String menuId) {
        return null;
        //return foodRepository.findFoodsByMenuId(menuId);
    }

    @Override
    public void addFood(JSONObject food) {

        //foodRepository.save(food);
    }
}
