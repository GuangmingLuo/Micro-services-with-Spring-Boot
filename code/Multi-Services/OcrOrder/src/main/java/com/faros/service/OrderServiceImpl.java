package com.faros.service;

import com.faros.entity.Order;
import com.faros.entity.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/5/11.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order addOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
