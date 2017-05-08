package com.faros.service;


import com.faros.entity.Menu;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
public interface MenuService {
    List<Menu> findMenuByRestaurantId(int restaurantId);
    void addMenu(Menu menu);
}
