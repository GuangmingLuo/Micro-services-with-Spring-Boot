package com.example;


import com.example.entities.Restaurant;
import com.example.entities.User;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String register(@ModelAttribute("message") final String message, Model model) {
        model.addAttribute("message",message);
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String registerPost(@Valid User user, @Valid int restaurant_id, RedirectAttributes redir) {
        user.setRestaurantId(restaurant_id);
        log.info("username is {} and password is {}, restaurant id is {}",user.getUsername(),user.getPassword(),user.getRestaurantId());
        User userExists = userService.findByUsername(user.getUsername());
        if(userExists !=null){
            redir.addFlashAttribute("message"," This username is already registered!");
        }else{
            redir.addFlashAttribute("message"," Successfully created new manager!");
            userService.saveUser(user);
            User myUser = userService.findByUsername(user.getUsername());
            userService.setUserRole(myUser.getId(),3); //3->manager
        }
        return "redirect:/admin/registration";
    }
}
