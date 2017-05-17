package com.faros.services;

import my.faros.model.User;
import my.faros.model.UserRole;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    protected String serviceUrl = "http://user-api-server/api";

    @Override
    public JSONObject findByUsername(String name) {
        return restTemplate.getForObject(serviceUrl+"/userByName?name={name}",JSONObject.class,name);
    }

    @Override
    public void saveUser(User user) {
        user.setUsername(bCryptPasswordEncoder.encode(user.getPassword()));
        HttpEntity<User> request = new HttpEntity<>(user);
        restTemplate.postForObject(serviceUrl+"/addUser", request, User.class);
    }

    @Override
    public void setUserRole(int userId, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);//1->user,2->admin,3->manager,4->employee
        HttpEntity<UserRole> request = new HttpEntity<>(userRole);
        restTemplate.postForObject(serviceUrl+"/saveUserRole", request, UserRole.class);
    }

    @Override
    public List<JSONObject> findUsersByRestaurantId(int restaurantId) {
        return restTemplate.getForObject(serviceUrl+"/usersByRestId?restaurantId={restaurantId}",List.class,restaurantId);
    }

    @Override
    public List<JSONObject> findEmployeesByRestaurantId(int restaurantId) {
        return restTemplate.getForObject(serviceUrl+"/employeesByRestId?restaurantId={restaurantId}",List.class,restaurantId);
    }
}
