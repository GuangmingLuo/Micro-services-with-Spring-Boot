package com.faros.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findFoodsByMenuId(int menuId);
    Food findFoodById(long id);
}
