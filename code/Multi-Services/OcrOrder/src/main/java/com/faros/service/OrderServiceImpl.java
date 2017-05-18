package com.faros.service;

import com.faros.entity.Order;
import com.faros.entity.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        int id = (int)orderRepository.count()+1;
        newOrder.setId(id);
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> findAllOrders(int restaurantId) {
        List<Order> orders= orderRepository.findAll();
        List<Order> result= new ArrayList<>();
        for(Order order:orders){
            if(order.getRestaurantId()==restaurantId){
                result.add(order);
            }
        }
        return result;
    }
}
