package com.example.entities;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuByRestaurantId(int restaurantId);
}
