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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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
    @RequestMapping(value="/restaurant/{name}", method= RequestMethod.GET)
    public String restaurant(@PathVariable String name, Model model){
        Restaurant rest;
        final SimpleGrantedAuthority AUTHORITY_MANAGER = new SimpleGrantedAuthority("MANAGER");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
            com.example.entities.User userExists = userService.findByUsername(user.getUsername());
            rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
            if(!rest.getName().equals(name)){
                return "redirect:/restaurant/"+rest.getName()+"/";
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
        model.addAttribute("restaurantName",rest.getName());
        model.addAttribute("address",rest.getAddress());
        model.addAttribute("introduction",rest.getIntroduction());
        List<Menu> menus = menuService.findMenuByRestaurantId(rest.getId());
        for (Menu menu:menus){
            menu.setFoods(foodService.findFoodsByMenuId(menu.getId()));
        }
        model.addAttribute("menus",menus);
        return "restaurant";
    }

    @RequestMapping(value="/restaurant/{name}/edit", method= RequestMethod.GET) //accessible by user with role "manager"
    public String createCategory(@PathVariable String name, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.example.entities.User userExists = userService.findByUsername(user.getUsername());
        Restaurant rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        if(!rest.getName().equals(name)){
            return "redirect:/error";         //if this manager is not bounded to this restaurant.
        }
        List<Menu> menus = menuService.findMenuByRestaurantId(rest.getId());
        model.addAttribute("menus",menus);
        for (Menu menu:menus){
            menu.setFoods(foodService.findFoodsByMenuId(menu.getId()));
        }
        return "editMenu";
    }

    @RequestMapping(value="/addMenuItem", method = RequestMethod.POST)
    public String addMenuItem(@Valid Food food, @Valid int menuId){
        food.setMenuId(menuId);
        log.info("Food to be add: {},{},{}",food.getName(),food.getPrice(),food.getMenuId());
        foodService.addFood(food);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.example.entities.User userExists = userService.findByUsername(user.getUsername());
        Restaurant rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        return "redirect:/restaurant/"+rest.getName()+"/edit";
    }

    @RequestMapping(value="/addMenu", method = RequestMethod.POST)
    public String addMenuItem(@Valid Menu menu){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.example.entities.User userExists = userService.findByUsername(user.getUsername());
        Restaurant rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
        menu.setRestaurantId(rest.getId());
        menuService.addMenu(menu);
        return "redirect:/restaurant/"+rest.getName()+"/edit";
    }
}
