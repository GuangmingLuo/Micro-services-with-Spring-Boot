package com.faros.services;

import com.faros.entities.User;

import java.util.List;

/*
 * Created by guang on 2017/4/12.
 */
public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
    void setUserRole(int userId, int roleId);
    List<User> findUsersByRestaurantId(int restaurantId);
    List<User> findEmployeesByRestaurantId(int restaurantId);
}
