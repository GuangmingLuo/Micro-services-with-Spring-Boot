package com.faros.entity;


import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guang on 2017/4/28.
 */
public interface OrderRepository extends MongoRepository<Order,Long> {
}
