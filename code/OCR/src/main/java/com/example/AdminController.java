package com.example;


import com.example.entities.Restaurant;
import com.example.entities.User;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.util.List;

/*
 * Created by guang on 4/13/2017.
 * This controller is used for renting business.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value= "/register",method= RequestMethod.GET)
    public String register(@RequestParam(value="message",required = false,defaultValue = "") String message,Model model) {
        model.addAttribute("message",message);
        List<Restaurant> restaurants = restaurantService.findAll();
        //.addAttribute(new User());
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String registerPost(@Valid User user, @Valid int restaurant_id, Model model) {
        user.setRestaurantId(restaurant_id);
        log.info("username is {} and password is {}, restaurant id is {}",user.getUsername(),user.getPassword(),user.getRestaurantId());
        User userExists = userService.findByUsername(user.getUsername());
        if(userExists !=null){
            model.addAttribute("message"," This username has been registered!");
        }else{
            model.addAttribute("message"," Success!");
            userService.saveUser(user);
        }
        return "register";
    }
}
