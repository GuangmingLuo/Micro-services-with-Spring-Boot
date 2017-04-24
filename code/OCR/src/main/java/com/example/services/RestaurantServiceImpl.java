package com.example.services;

import com.example.entities.Restaurant;
import com.example.entities.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findRestaurantById(int id) {
        return restaurantRepository.findRestaurantById(id);
    }
}
