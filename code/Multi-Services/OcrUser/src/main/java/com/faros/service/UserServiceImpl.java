package com.faros.service;

import com.faros.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by guang on 2017/4/12.
 * Implementation for user service
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
        //User myUser = findByUsername(user.getUsername());
        //setUserRole(myUser.getId());
    }

    @Override
    public void setUserRole(int userId, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);//1->user,2->admin,3->manager,4->employee
        //log.info("The UserId is {}, the roleId is {}", userRole.getUserId(),userRole.getRoleId());
        userRoleRepository.save(userRole);
    }

    @Override
    public List<User> findUsersByRestaurantId(int restaurantId) {
        List<User> users = userRepository.findAll();
        List<User> results = null;
        for(User user:users){
            if(user.getRestaurantId()==restaurantId)
                results.add(user);
        }
        return results;
    }

    @Override
    public List<User> findEmployeesByRestaurantId(int restaurantId) {
        List<User> users = userRepository.findAll();
        List<UserRole> userRoles = userRoleRepository.findAll();
        List<User> results = new ArrayList<>();
        for(User user:users){
            if(user.getRestaurantId()==restaurantId){ //if this user belongs to the given restaurant
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(4);
                if(userRoles.contains(userRole)){ //if user has role of 4->employee
                    results.add(user);
                }
            }
        }
        return results;
    }
}
