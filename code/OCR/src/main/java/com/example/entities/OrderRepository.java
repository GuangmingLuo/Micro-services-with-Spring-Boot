package com.example.entities;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by guang on 2017/4/28.
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}
