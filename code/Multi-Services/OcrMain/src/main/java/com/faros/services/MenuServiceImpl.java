package com.faros.services;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    final String menuUrl = "http://localhost:82/api/menu?restaurantId={restaurantId}";
    @Override
    public List<JSONObject> findMenuByRestaurantId(String restaurantId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(menuUrl, List.class,restaurantId);
    }

    @Override
    public void addMenu(JSONObject menu) {
        //menuRepository.save(menu);
    }
}
