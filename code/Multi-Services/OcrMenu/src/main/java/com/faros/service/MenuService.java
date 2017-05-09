package com.faros.service;


import com.faros.entity.Menu;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuService {
    ArrayList<Menu> findMenuByRestaurantId(int restaurantId);
    void addMenu(Menu menu);
}
