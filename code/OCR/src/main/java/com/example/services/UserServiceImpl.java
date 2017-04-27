package com.example.services;

import com.example.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

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
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        User myUser = findByUsername(user.getUsername());
        setUserRole(myUser.getId());
    }

    @Override
    public void setUserRole(int userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(3);//3->manager
        log.info("The UserId is {}, the roleId is {}", userRole.getUserId(),userRole.getRoleId());
        userRoleRepository.save(userRole);
    }
}
