package com.example;

import com.example.entities.Food;
import com.example.entities.Menu;
import com.example.entities.Restaurant;
import com.example.services.FoodService;
import com.example.services.MenuService;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private MenuService menuService;
    @Autowired
    private FoodService foodService;
    @RequestMapping(value="/restaurant", method= RequestMethod.GET)
    public String restaurant(@RequestParam(value="name",defaultValue = "null")String name, Model model){
        Restaurant rest;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
            com.example.entities.User userExists = userService.findByUsername(user.getUsername());
            rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        }else{
            rest = restaurantService.findRestaurantByName(name);
        }
        model.addAttribute("restaurantName",rest.getName());
        model.addAttribute("address",rest.getAddress());
        model.addAttribute("introduction",rest.getIntroduction());
        List<Menu> menus = menuService.findMenuByRestaurantId(rest.getId());
        model.addAttribute("menus",menus);
        List<List<Food>> foodsList = new ArrayList<>();
        for (Menu menu:menus){
            menu.setFoods(foodService.findFoodsByMenuId(menu.getId()));
        }
        //model.addAttribute("foodsList",foodsList);
        return "restaurant";
    }



}
