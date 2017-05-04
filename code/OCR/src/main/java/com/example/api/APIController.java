package com.example.api;

/*
 * Created by guang on 2017/5/3.
 * RESTful web services for menu, HTTP requests are handled by this controller
 */
import com.example.entities.Menu;
import com.example.entities.Restaurant;
import com.example.services.FoodService;
import com.example.services.MenuService;
import com.example.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private FoodService foodService;

    /*
    * This api returns a restaurant entity by a restaurant name
    * */
    @RequestMapping("/restaurant")
    public Restaurant restaurant(@RequestParam(value="name") String name) {
        return restaurantService.findRestaurantByName(name);
    }
    /*
    * This api returns a list of menu entities by a restaurant id
    * */
    @RequestMapping("/menu")
    public List<Menu> menu(@RequestParam(value="restaurantId") int restaurantId) {
        List<Menu> menus = menuService.findMenuByRestaurantId(restaurantId);
        for (Menu menu:menus){
            menu.setFoods(foodService.findFoodsByMenuId(menu.getId()));
        }
        return menus;
    }
}
