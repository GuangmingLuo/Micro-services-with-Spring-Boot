package com.example.entities;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by guang on 2017/4/24.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findRestaurantById(int id);
    Restaurant findRestaurantByName(String name);
}
