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

import java.util.ArrayList;
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
    public ArrayList<Menu> menu(@RequestParam(value="restaurantId") int restaurantId) {
        return menuService.findMenuByRestaurantId(restaurantId);
    }
    /*
    * This api is used to create test data for developer
    * */
    @RequestMapping("/create")
    public String create() {
        Menu menu1 = new Menu();
        menu1.setId(1);
        menu1.setName("Chicken");
        menu1.setRestaurantId(1);
        menuService.addMenu(menu1);
        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setName("Sandwich");
        menu2.setRestaurantId(1);
        menuService.addMenu(menu2);
        Menu menu3 = new Menu();
        menu3.setId(3);
        menu3.setName("Pizza");
        menu3.setRestaurantId(2);
        menuService.addMenu(menu3);
        Menu menu4 = new Menu();
        menu4.setId(4);
        menu4.setName("Drink");
        menu4.setRestaurantId(1);
        menuService.addMenu(menu4);
        return "Success!";
    }
}
