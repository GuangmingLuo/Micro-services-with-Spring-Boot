package com.faros.services;

import com.faros.entities.Order;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by guang on 2017/5/8.
 */
public interface OrderService {
    void save(Order order);
    boolean checkStatus();
    List<JSONObject> findAll();
}