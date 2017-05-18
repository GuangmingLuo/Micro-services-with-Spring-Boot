package com.faros.service;

import com.faros.entity.Food;
import com.faros.entity.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("FoodService")
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;
    private static final Logger log = LoggerFactory.getLogger(FoodServiceImpl.class);
    @Override
    public List<Food> findFoodsByMenuId(int menuId) {
        return foodRepository.findFoodsByMenuId(menuId);
    }

    @Override
    public Food findFoodById(long foodId) {
        return foodRepository.findFoodById(foodId);
    }

    @Override
    public Food addFood(Food food) {
        log.info("food is: {}",food.toString());
        int id = (int)foodRepository.count() + 1;
        food.setId(id);
        food.setDiscount(1);
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(long id) {
        Food food = foodRepository.findFoodById(id);
        if(food != null){
            foodRepository.delete(food);
        }
    }
}
