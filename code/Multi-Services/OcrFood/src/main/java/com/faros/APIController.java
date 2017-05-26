package com.faros;

/*
 * Created by guang on 2017/5/3.
 * RESTful web services for menu, HTTP requests are handled by this controller
 */
import com.faros.entity.Food;
import com.faros.service.FoodService;
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
    private FoodService foodService;

    @RequestMapping
    public String info() {
        String info = "Available api under ip:83/api: <br />";
        info += "/foods?menuId=xxx <br />";
        info += "/food?foodId=xxx <br />";
        info += "/addFood (POST: @RequestBody Food f) <br />";
        info += "/deleteFood/{id}<br />";
        return info;
    }
    /*
    * This api returns a list of food entities by a menu id
    * */
    @RequestMapping("/foods")
    public List<Food> foods(@RequestParam(value="menuId") int menuId) {
        return foodService.findFoodsByMenuId(menuId);
    }

    /*
        * This api returns a list of food entities by a menu id
        * */
    @RequestMapping("/food")
    public Food food(@RequestParam(value="foodId") int foodId) {
        return foodService.findFoodById(foodId);
    }

    /*
    * This api do a post to database
    * */
    @RequestMapping(value = "/addFood",method = RequestMethod.POST)
    public ResponseEntity<?> addFood(@RequestBody Food food) {
        Food result = foodService.addFood(food);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    * This api delete a food item from database
    * */
    @DeleteMapping("/deleteFood/{id}")
    public void deleteFood(@PathVariable("id") long id) {
        foodService.deleteFood(id);
    }

}
