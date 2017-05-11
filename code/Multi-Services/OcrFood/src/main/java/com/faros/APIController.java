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
        info += "/food?menuId=xxx <br />";
        info += "/addFood (POST: @RequestBody Food f) <br />";
        return info;
    }
    /*
    * This api returns a list of food entities by a menu id
    * */
    @RequestMapping("/food")
    public List<Food> foods(@RequestParam(value="menuId") int menuId) {
        return foodService.findFoodsByMenuId(menuId);
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
    * This api is used to create test data for developer
    * */
//    @RequestMapping("/create")
//    public String create() {
//        Food food1 = new Food(1,"Hot Wings",3.79f,1,1);foodService.addFood(food1);
//        Food food2 = new Food(2,"Chicken Tenders",6.99f,1,1);foodService.addFood(food2);
//        Food food3 = new Food(3,"Zinger",5.00f,1,2);foodService.addFood(food3);
//        Food food4 = new Food(4,"BigMac",6.89f,1,2);foodService.addFood(food4);
//        Food food5 = new Food(5,"Margarita",5.12f,1,3);foodService.addFood(food5);
//        Food food6 = new Food(6,"Hawaii",6.32f,1,3);foodService.addFood(food6);
//        Food food7 = new Food(7,"Chicken legs",4.79f,1,1);foodService.addFood(food7);
//        Food food8 = new Food(8,"Mac",5.79f,1,2);foodService.addFood(food8);
//        Food food9 = new Food(9,"Cola",1.5f,1,4);foodService.addFood(food9);
//        Food food10 = new Food(10,"Beer",2.0f,1,4);foodService.addFood(food10);
//        return "Success!";
//    }
}
