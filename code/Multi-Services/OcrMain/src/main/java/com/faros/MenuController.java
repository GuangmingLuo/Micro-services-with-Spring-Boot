package com.faros;

import com.faros.services.FoodService;
import com.faros.services.MenuService;
import com.faros.services.RestaurantService;
import com.faros.services.UserService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/24.
 */
@Controller
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private FoodService foodService;
    String errorMessage = null;
    @RequestMapping(value="/restaurant/{name}", method= RequestMethod.GET)
    public String restaurant(@PathVariable String name, Model model){
        JSONObject rest;
        final SimpleGrantedAuthority AUTHORITY_MANAGER = new SimpleGrantedAuthority("MANAGER");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
            com.faros.entities.User userExists = userService.findByUsername(user.getUsername());
            rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
            if(!rest.getAsString("name").equals(name)){
                return "redirect:/restaurant/"+rest.getAsString("name")+"/";
            }
            log.info("User content is {}",user.toString());
            if(user.getAuthorities().contains(AUTHORITY_MANAGER)){
                model.addAttribute("isManager",true);
            }
            log.info("MANAGER ? {}",user.getAuthorities().contains(AUTHORITY_MANAGER));
        }else{
            rest = restaurantService.findRestaurantByName(name);
            if(rest ==null){
                return "redirect:/login/";
            }
            model.addAttribute("isManager",false);
        }
        model.addAttribute("restaurantName",rest.getAsString("name"));
        model.addAttribute("address",rest.getAsString("address"));
        model.addAttribute("introduction",rest.getAsString("introduction"));
        List<JSONObject> menus = apiErrorHandle(rest);
        model.addAttribute("menus",menus);
        model.addAttribute("errorMessage",errorMessage);
        return "restaurant";
    }

    @RequestMapping(value="/restaurant/{name}/edit", method= RequestMethod.GET) //accessible by user with role "manager"
    public String createCategory(@PathVariable String name, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.faros.entities.User userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        if(!rest.getAsString("name").equals(name)){
            return "redirect:/error";         //if this manager is not bounded to this restaurant.
        }
        List<JSONObject> menus = apiErrorHandle(rest);
        model.addAttribute("menus",menus);
        model.addAttribute("errorMessage",errorMessage);
        return "editMenu";
    }

    @RequestMapping(value="/addMenuItem", method = RequestMethod.POST)
    public String addMenuItem(@Valid JSONObject food, @Valid int menuId){
        food.put("menuId",menuId);
        //log.info("Food to be add: {},{},{}",food.getName(),food.getPrice(),food.getMenuId());
        foodService.addFood(food);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.faros.entities.User userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        return "redirect:/restaurant/"+rest.getAsString("name")+"/edit";
    }

    @RequestMapping(value="/addMenu", method = RequestMethod.POST)
    public String addMenuItem(@Valid JSONObject menu){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.faros.entities.User userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        menu.put("restaurantId",rest.getAsString("id"));
        menuService.addMenu(menu);
        return "redirect:/restaurant/"+rest.getAsString("name")+"/edit";
    }

    private List<JSONObject> apiErrorHandle(JSONObject rest){
        List<JSONObject> menus = null; errorMessage = "";
        try {
            menus = menuService.findMenuByRestaurantId(rest.getAsString("id"));
            for (JSONObject menu:menus){
                try {
                    menu.put("foods",foodService.findFoodsByMenuId(menu.getAsString("id")));
                } catch (Exception e) {
                    errorMessage = "The foodService is down!";
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            errorMessage += "The menuService is down! \n";
            e.printStackTrace();
        }
        return menus;
    }
}
