package com.faros.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.faros.model.Order;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
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
    public boolean checkApiStatus() {
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
    public List<JSONObject> findAll(int restaurantId) throws IOException {
        //return restTemplate.getForObject(serviceUrl+"/orders?restaurantId={restaurantId}", List.class,restaurantId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode orders = restTemplate.getForObject(serviceUrl+"/orders?restaurantId={restaurantId}", JsonNode.class,restaurantId);
        ArrayList<JSONObject> orderList = mapper.readValue(
                mapper.treeAsTokens(orders),
                new TypeReference<List<JSONObject>>(){}
        );
        return orderList;
    }

    @Override
    public void changeOrderStatus(long id, String newStatus) {
        Order order = restTemplate.getForObject(serviceUrl+"/orderById?id={id}", Order.class,id);
        order.setStatus(newStatus);
        HttpEntity<Order> request = new HttpEntity<>(order);
        restTemplate.postForObject(serviceUrl+"/saveOrder", request, Order.class);
    }
}
