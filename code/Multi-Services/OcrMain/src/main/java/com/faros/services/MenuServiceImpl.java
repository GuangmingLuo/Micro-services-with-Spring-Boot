package com.faros.services;

import com.faros.entities.Menu;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    final String menuUrl = "http://localhost:82/api/menu?restaurantId={restaurantId}";
    final String addMenuUrl = "http://localhost:82/api/addMenu";
    @Override
    public ArrayList<JSONObject> findMenuByRestaurantId(String restaurantId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode menus = restTemplate.getForObject(menuUrl, JsonNode.class,restaurantId);
        ArrayList<JSONObject> menuList = mapper.readValue(
                mapper.treeAsTokens(menus),
                new TypeReference<List<JSONObject>>(){}
        );
        return menuList;
    }

    @Override
    public void addMenu(Menu menu) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Menu> request = new HttpEntity<>(menu);
        restTemplate.postForObject(addMenuUrl, request, Menu.class);
    }
}
