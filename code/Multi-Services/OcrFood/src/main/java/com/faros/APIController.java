package com.faros;

/*
 * Created by guang on 2017/5/3.
 * RESTful web services for menu, HTTP requests are handled by this controller
 */
import com.faros.entity.Food;
import com.faros.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private FoodService foodService;

    /*
    * This api returns a list of menu entities by a restaurant id
    * */
    @RequestMapping("/food")
    public List<Food> menu(@RequestParam(value="menuId") int menuId) {
        return foodService.findFoodsByMenuId(menuId);
    }
}
