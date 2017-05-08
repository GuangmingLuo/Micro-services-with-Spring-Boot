package com.faros.services;


import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuService {
    List<JSONObject> findMenuByRestaurantId(String restaurantId);
    void addMenu(JSONObject menu);
}
