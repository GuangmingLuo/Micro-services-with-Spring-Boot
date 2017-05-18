package com.faros.services;

import my.faros.model.Order;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by guang on 2017/5/8.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://order-api-server/api";
    @Override
    public boolean checkStatus() {
        String status = restTemplate.getForObject(serviceUrl+"/status", Boolean.class).toString();
        if(status.equals("true")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void save(Order order) {
        HttpEntity<Order> request = new HttpEntity<>(order);
        restTemplate.postForObject(serviceUrl+"/addOrder", request, Order.class);
    }

    @Override
    public List<JSONObject> findAll(int restaurantId) {
        return restTemplate.getForObject(serviceUrl+"/orders?restaurantId={restaurantId}", List.class,restaurantId);
    }
}
