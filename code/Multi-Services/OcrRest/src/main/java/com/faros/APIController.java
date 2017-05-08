package com.faros;

/*
 * Created by guang on 2017/5/3.
 * RESTful web services for menu, HTTP requests are handled by this controller
 */
import com.faros.entity.Restaurant;
import com.faros.service.RestaurantService;
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


    @RequestMapping
    public String info() {
        return "/menu?restaurantId=X";
    }

    /*
    * This api returns a restaurant entity by a restaurant name
    * */
    @RequestMapping("/restaurantByName")
    public Restaurant restaurantByName(@RequestParam(value="name") String name) {
        return restaurantService.findRestaurantByName(name);
    }
    @RequestMapping("/restaurantById")
    public Restaurant restaurantById(@RequestParam(value="id") String id) {
        return restaurantService.findRestaurantByName(id);
    }
    /*
    * This api returns all restaurant entities
    * */
    @RequestMapping("/restaurants")
    public List<Restaurant> restaurants() {
        return restaurantService.findAll();
    }

    @RequestMapping("/create")
    public Restaurant create(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("KFC");
        restaurant.setAddress("Leuven");
        restaurant.setIntroduction("blablabla");
        restaurantService.save(restaurant);
        return restaurant;
    }
}
