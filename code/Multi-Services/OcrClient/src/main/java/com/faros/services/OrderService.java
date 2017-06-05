package com.faros.services;

import my.faros.model.Order;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by guang on 2017/5/8.
 */
public interface OrderService {
    /*
    * This method is used to save an order entity
    * */
    void save(Order order);

    /*
    * This method is used to check if order api available or not
    * */
    boolean checkApiStatus();

    /*
    * This method is used to find all the available orders by restaurantId
    * */
    List<JSONObject> findAll(int restaurantId) throws IOException;

    /*
    * This method is used to change the status of an order by id and newStatus
    * */
    void changeOrderStatus(long id, String newStatus);
}
