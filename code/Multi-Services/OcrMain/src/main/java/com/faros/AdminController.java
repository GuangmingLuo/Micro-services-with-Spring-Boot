package com.faros;


import com.faros.entities.Restaurant;
import com.faros.services.UserService;
import com.faros.services.RestaurantService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * This controller is used for renting business.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value= "/registration",method= RequestMethod.GET)
    public String register(@ModelAttribute("message") final String message1,@ModelAttribute("messageRest") final String message2, Model model) {
        model.addAttribute("message1",message1);
        model.addAttribute("message2",message2);
        List<JSONObject> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

    @RequestMapping(value= "/addRestaurant",method= RequestMethod.POST)
    public String addRestaurant(@Valid Restaurant restaurant, RedirectAttributes redir){
        restaurantService.addRestaurant(restaurant);
        redir.addFlashAttribute("messageRest"," Succeed to create a Restaurant!");
        return "redirect:/admin/registration";
    }
}
