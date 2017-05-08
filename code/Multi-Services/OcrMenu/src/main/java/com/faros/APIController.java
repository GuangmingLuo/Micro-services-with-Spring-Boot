package com.faros;

/*
 * Created by guang on 2017/5/3.
 * RESTful web services for menu, HTTP requests are handled by this controller
 */
import com.faros.entity.Menu;
import com.faros.service.MenuService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private MenuService menuService;


    /*
    * This api returns a list of menu entities by a restaurant id
    * */
    @RequestMapping("/menu")
    public List<Menu> menu(@RequestParam(value="restaurantId") int restaurantId) {
        List<Menu> menus = menuService.findMenuByRestaurantId(restaurantId);
        for (Menu menu:menus){
            //List<JSONObject> foods = foodService.findFoodsByMenuId(menu.getId());
            //menu.setFoods(foods);
        }
        return menus;
    }
}
