package com.faros.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.faros.model.Menu;
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
 * Created by guang on 2017/4/25.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://menu-api-server/api";
    @Override
    public ArrayList<JSONObject> findMenuByRestaurantId(String restaurantId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode menus = restTemplate.getForObject(serviceUrl+"/menu?restaurantId={restaurantId}", JsonNode.class,restaurantId);
        ArrayList<JSONObject> menuList = mapper.readValue(
                mapper.treeAsTokens(menus),
                new TypeReference<List<JSONObject>>(){}
        );
        return menuList;
    }

    @Override
    public void addMenu(Menu menu) {
        HttpEntity<Menu> request = new HttpEntity<>(menu);
        restTemplate.postForObject(serviceUrl+"/addMenu", request, Menu.class);
    }
}
