package com.faros.services;


import my.faros.model.Menu;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuService {
    /*
    * This method is used to find a list of menu entities (JSONObject) by restaurantId
    * */
    List<JSONObject> findMenuByRestaurantId(String restaurantId) throws IOException;

    /*
    * This method is used to add a menu entity
    * */
    void addMenu(Menu menu);
}
