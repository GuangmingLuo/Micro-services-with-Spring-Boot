package com.faros;

import com.faros.services.*;
import my.faros.model.Food;
import my.faros.model.Menu;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
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
    @Autowired
    private OrderService orderService;

    String errorMessage = null;

    /*
    * This method returns a home page for each restaurant
    * Multi-tenancy technology is used here: web content is different for managers and non-managers
    * Error correction for restaurant name in the url: redirect to the right url for authenticated user; redirect to home page for anonymousUser.
    * */
    @RequestMapping(value="/restaurant/{name}", method= RequestMethod.GET)
    public String restaurant(@PathVariable String name, @RequestParam(required = false) String tableId, Model model){
        JSONObject rest;
        final SimpleGrantedAuthority AUTHORITY_MANAGER = new SimpleGrantedAuthority("MANAGER");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
            JSONObject userExists = userService.findByUsername(user.getUsername());
            rest = restaurantService.findRestaurantById(Integer.parseInt(userExists.getAsString("restaurantId")));
            if(!rest.getAsString("name").equals(name)){
                return "redirect:/restaurant/"+rest.getAsString("name")+"/";
            }
            if(user.getAuthorities().contains(AUTHORITY_MANAGER)){
                model.addAttribute("isManager",true);
            }
        }else{
            rest = restaurantService.findRestaurantByName(name);
            if(rest ==null){
                return "redirect:/";
            }
            model.addAttribute("isManager",false);
        }
        model.addAttribute("restaurantName",rest.getAsString("name"));
        model.addAttribute("address",rest.getAsString("address"));
        model.addAttribute("introduction",rest.getAsString("introduction"));
        List<JSONObject> menus = apiErrorHandle(rest);
        model.addAttribute("menus",menus);
        if(tableId!=null){
            model.addAttribute("tableId",tableId);
        }else{
            model.addAttribute("",tableId);
        }
        model.addAttribute("errorMessage",errorMessage);
        return "restaurant";
    }

    /*
    * This method returns menu editing page
    * only accessible by user with role "manager"
    * */
    @RequestMapping(value="/restaurant/{name}/edit", method= RequestMethod.GET)
    public String menuEditing(@PathVariable String name, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        JSONObject userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(Integer.parseInt(userExists.getAsString("restaurantId")));
        if(!rest.getAsString("name").equals(name)){
            return "redirect:/error";         //if this manager is not bounded to this restaurant.
        }
        List<JSONObject> menus = apiErrorHandle(rest);
        model.addAttribute("menus",menus);
        model.addAttribute("errorMessage",errorMessage);
        return "editMenu";
    }

    /*
    * This post method is used to add a menu item/food to a menu/category
    * only accessible by user with role "manager"
    * */
    @RequestMapping(value="/addMenuItem", method = RequestMethod.POST)
    public String addMenuItem(@Valid Food food, @Valid int menuId){
        food.setMenuId(menuId);
        log.info("Food to be add: {},{},{}",food.getName(),food.getPrice(),food.getMenuId());
        foodService.addFood(food);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        JSONObject userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(Integer.parseInt(userExists.getAsString("restaurantId")));
        return "redirect:/restaurant/"+rest.getAsString("name")+"/edit";
    }

    /*
    * This post method is used to add a menu/category to a restaurant
    * only accessible by user with role "manager"
    * */
    @RequestMapping(value="/addMenu", method = RequestMethod.POST)
    public String addMenuItem(@Valid Menu menu){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        JSONObject userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(Integer.parseInt(userExists.getAsString("restaurantId")));
        menu.setRestaurantId(Integer.parseInt(rest.getAsString("id")));
        menuService.addMenu(menu);
        return "redirect:/restaurant/"+rest.getAsString("name")+"/edit";
    }

    /*
    * This post method is used to delete a menu item/food to a menu/category
    * only accessible by user with role "manager"
    * */
    @RequestMapping(value="/restaurant/deleteFood/{id}",method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFood(@PathVariable String id){
        foodService.deleteFood(id);
    }

    /*
    * This is internal method to handle error exception for api calls
    * Display the corresponding error message in the web page.
    * */
    private List<JSONObject> apiErrorHandle(JSONObject rest){
        List<JSONObject> menus = null; errorMessage = "";
        try {
            menus = menuService.findMenuByRestaurantId(rest.getAsString("id"));
            for (JSONObject menu:menus){
                try {
                    menu.put("foods",foodService.findFoodsByMenuId(menu.getAsString("id")));
                } catch (Exception e) {
                    errorMessage = "The foodService is down! \n";
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            errorMessage += "The menuService is down! \n";
            e.printStackTrace();
        }
        try {
            orderService.checkApiStatus();
        }catch (Exception e){
            errorMessage += "The OrderService is down! \n";
            e.printStackTrace();
        }
        return menus;
    }
}
