package com.faros.service;

import com.faros.entity.Order;

import java.util.List;

/**
 * Created by guang on 2017/5/11.
 */
public interface OrderService {
    Order addOrder(Order newOrder);
    List<Order> findAllOrders();
}
