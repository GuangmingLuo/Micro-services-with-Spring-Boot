package com.example.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findMenuByRestaurantId(int restaurantId);
}
