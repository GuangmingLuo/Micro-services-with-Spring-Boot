package com.example;

import com.example.entities.Restaurant;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guang on 2017/4/24.
 */
@Controller

public class BusinessController {

    //private static final Logger log = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @RequestMapping("/restaurant")
    public String restaurant(@RequestParam(value="name",defaultValue = "null")String name, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
            com.example.entities.User userExists = userService.findByUsername(user.getUsername());
            Restaurant rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
            model.addAttribute("restaurantName",rest.getName());
            model.addAttribute("address",rest.getAddress());
            model.addAttribute("introduction",rest.getIntroduction());
        }else{
            model.addAttribute("restaurantName",name);
            Restaurant rest = restaurantService.findRestaurantByName(name);
            model.addAttribute("address",rest.getAddress());
            model.addAttribute("introduction",rest.getIntroduction());
        }
        return "restaurant";
    }
    

}
