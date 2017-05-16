package com.faros.services;


import my.faros.model.User;
import net.minidev.json.JSONObject;
import java.util.List;

/*
 * Created by guang on 2017/4/12.
 */
public interface UserService {
    JSONObject findByUsername(String username);
    void saveUser(User user);
    void setUserRole(int userId, int roleId);
    List<JSONObject> findUsersByRestaurantId(int restaurantId);
    List<JSONObject> findEmployeesByRestaurantId(int restaurantId);
}
