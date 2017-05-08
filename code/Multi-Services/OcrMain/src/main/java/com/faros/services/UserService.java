package com.faros.services;

import com.faros.entities.User;

/*
 * Created by guang on 2017/4/12.
 */
public interface UserService {
    public User findByUsername(String username);
    public void saveUser(User user);
    public void setUserRole(int userId, int roleId);
}
