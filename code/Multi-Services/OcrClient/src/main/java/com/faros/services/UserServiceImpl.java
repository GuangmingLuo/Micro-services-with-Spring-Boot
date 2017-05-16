package com.faros.services;

import my.faros.model.User;
import my.faros.model.UserRole;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/*
 * Created by guang on 2017/4/12.
 * Implementation for user service
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    final String userByNameUrl = "http://localhost:85/api/userByName?name={name}";
    final String saveUserUrl = "http://localhost:85/api/addUser";
    final String saveUserRoleUrl = "http://localhost:85/api/saveUserRole";
    final String usersByRestIdUrl = "http://localhost:85/api/usersByRestId?restaurantId={restaurantId}";
    final String employeesByRestIdUrl = "http://localhost:85/api/employeesByRestId?restaurantId={restaurantId}";

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public JSONObject findByUsername(String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(userByNameUrl,JSONObject.class,name);
    }

    @Override
    public void saveUser(User user) {
        user.setUsername(bCryptPasswordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(user);
        restTemplate.postForObject(saveUserUrl, request, User.class);
    }

    @Override
    public void setUserRole(int userId, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);//1->user,2->admin,3->manager,4->employee
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserRole> request = new HttpEntity<>(userRole);
        restTemplate.postForObject(saveUserRoleUrl, request, UserRole.class);
    }

    @Override
    public List<JSONObject> findUsersByRestaurantId(int restaurantId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(usersByRestIdUrl,List.class,restaurantId);
    }

    @Override
    public List<JSONObject> findEmployeesByRestaurantId(int restaurantId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(employeesByRestIdUrl,List.class,restaurantId);
    }
}
