package com.faros.services;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Override
    public List<JSONObject> findMenuByRestaurantId(String restaurantId) {
        return null;
        //return menuRepository.findMenuByRestaurantId(restaurantId);
    }

    @Override
    public void addMenu(JSONObject menu) {
        //menuRepository.save(menu);
    }
}
