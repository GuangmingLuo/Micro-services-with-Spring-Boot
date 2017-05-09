package com.faros.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuRepository extends MongoRepository<Menu, String> {
    ArrayList<Menu> findMenuByRestaurantId(int restaurantId);
}
