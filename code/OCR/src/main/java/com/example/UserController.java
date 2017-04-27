package com.example;

import com.example.entities.Restaurant;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by guang on 2017/4/3.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value= "/login",method=GET)
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            com.example.entities.User userExists = userService.findByUsername(user.getUsername());
            Restaurant rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
            return "redirect:/restaurant/"+rest.getName()+"/";
        }
        return "login";
    }

}
