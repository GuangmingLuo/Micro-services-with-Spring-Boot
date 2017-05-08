package com.faros;


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
    public String register(@ModelAttribute("message") final String message, Model model) {
        model.addAttribute("message",message);
        List<JSONObject> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

}
