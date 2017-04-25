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
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

//    @RequestMapping(value= "/register",method=RequestMethod.GET)
//    public String register(@RequestParam(value="message",required = false,defaultValue = "") String message,Model model) {
//        model.addAttribute("message",message);
//        List<Restaurant> restaurants = restaurantService.findAll();
//        //.addAttribute(new User());
//        model.addAttribute("restaurants",restaurants);
//        return "register";
//    }

    @RequestMapping(value= "/login",method=GET)
    public String login() {
        return "login";
    }


}
