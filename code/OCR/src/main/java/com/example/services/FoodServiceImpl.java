package com.example.services;

import com.example.entities.Food;
import com.example.entities.FoodRepository;
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
    @Override
    public List<Food> findFoodsByMenuId(int menuId) {
        return foodRepository.findFoodsByMenuId(menuId);
    }
}
