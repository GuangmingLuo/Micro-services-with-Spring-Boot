package com.faros.services;

import my.faros.model.Order;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by guang on 2017/5/8.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    final String addOrderUrl = "http://localhost:84/api/addOrder";
    final String ordersUrl = "http://localhost:84/api/orders";
    final String statusUrl = "http://localhost:84/api/status";

    @Override
    public boolean checkStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String status = restTemplate.getForObject(statusUrl, Boolean.class).toString();
        if(status.equals("true")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void save(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Order> request = new HttpEntity<>(order);
        restTemplate.postForObject(addOrderUrl, request, Order.class);
    }

    @Override
    public List<JSONObject> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ordersUrl, List.class);
    }
}
