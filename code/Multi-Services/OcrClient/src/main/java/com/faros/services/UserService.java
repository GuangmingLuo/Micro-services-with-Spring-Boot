package com.faros.services;


import my.faros.model.User;
import net.minidev.json.JSONObject;
import java.util.List;

/*
 * Created by guang on 2017/4/12.
 */
public interface UserService {
    /*
    * This method is used to get a user entity (JSONObject) by it's username
    * */
    JSONObject findByUsername(String username);

    /*
    * This method is used to save a user entity (JSONObject
    * */
    void saveUser(User user);

    /*
    * This method is used to set a role for a user by userId and roleId
    * */
    void setUserRole(int userId, int roleId);

    /*
    * This method is used to get all user entities (JSONObjects) by restaurantId
    * */
    List<JSONObject> findUsersByRestaurantId(int restaurantId);

    /*
    * This method is used to get all employees (JSONObjects) by restaurantId
    * */
    List<JSONObject> findEmployeesByRestaurantId(int restaurantId);
}
