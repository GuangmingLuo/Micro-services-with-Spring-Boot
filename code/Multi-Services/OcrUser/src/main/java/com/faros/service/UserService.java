package com.faros.service;

import com.faros.model.User;

import java.util.List;

/*
 * Created by guang on 2017/4/12.
 */
public interface UserService {
    User findByUsername(String username);
    User saveUser(User user);
    void setUserRole(int userId, int roleId);
    List<User> findUsersByRestaurantId(int restaurantId);
    List<User> findEmployeesByRestaurantId(int restaurantId);
}
