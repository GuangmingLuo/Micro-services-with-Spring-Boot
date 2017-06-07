package com.faros;

import com.faros.services.RestaurantService;
import my.faros.model.Restaurant;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;

/*
 * Created by guang on 4/13/2017.
 * This controller is used for administration business.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    /*
    * This register page is used for admin to register a manager account or create a new restaurant
    * */
    @RequestMapping(value= "/registration",method= RequestMethod.GET)
    public String register(@ModelAttribute("message") final String message1,@ModelAttribute("messageRest") final String message2, Model model) {
        model.addAttribute("message1",message1);
        model.addAttribute("message2",message2);
        List<JSONObject> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

    /*
    * Post method to add a new restaurant
    * */
    @RequestMapping(value= "/addRestaurant",method= RequestMethod.POST)
    public String addRestaurant(@Valid Restaurant restaurant, RedirectAttributes redir){
        restaurantService.addRestaurant(restaurant);
        redir.addFlashAttribute("messageRest"," Succeed to create a Restaurant!");
        return "redirect:/admin/registration";
    }
}
