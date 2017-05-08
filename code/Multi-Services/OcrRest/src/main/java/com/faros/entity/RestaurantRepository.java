package com.faros.entity;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantRepository extends MongoRepository<Restaurant, String>{
    Restaurant findRestaurantById(int id);
    Restaurant findRestaurantByName(String name);
    List<Restaurant> findAll();

}
