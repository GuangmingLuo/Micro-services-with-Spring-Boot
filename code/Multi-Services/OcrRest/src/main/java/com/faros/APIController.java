package com.faros;

/*
 * Created by guang on 2017/5/3.
 * RESTful web services for menu, HTTP requests are handled by this controller
 */
import com.faros.entity.Restaurant;
import com.faros.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        return restaurantService.findRestaurantById(id);
    }
    /*
    * This api provide post api to add a new restaurant entity
    * */
    @RequestMapping(value = "/addRestaurant",method = RequestMethod.POST)
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant result = restaurantService.save(restaurant);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    * This api returns all restaurant entities
    * */
    @RequestMapping("/restaurants")
    public List<Restaurant> restaurants() {
        return restaurantService.findAll();
    }

    @RequestMapping("/create")
    public String create(){
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1);
        restaurant1.setName("KFC");
        restaurant1.setAddress("Blijd-Inkomststraat 155, 3000 Leuven");
        restaurant1.setIntroduction("blablabla");
        restaurantService.save(restaurant1);
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2);
        restaurant2.setName("PizzaHut");
        restaurant2.setAddress("Korenmarkt 34, 9000 Gent");
        restaurant2.setIntroduction("blablabla");
        restaurantService.save(restaurant2);
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setId(3);
        restaurant3.setName("MacHome");
        restaurant3.setAddress("Tijstrat 11, 3001 Hevelee");
        restaurant3.setIntroduction("blablabla");
        restaurantService.save(restaurant3);
        return "success!";
    }
}
