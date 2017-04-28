package com.example;

import com.example.entities.Restaurant;
import com.example.entities.User;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by guang on 2017/4/3.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value= "/login",method=GET)
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal().equals("anonymousUser"))) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            com.example.entities.User userExists = userService.findByUsername(user.getUsername());
            Restaurant rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
            return "redirect:/restaurant/"+rest.getName()+"/";
        }
        return "login";
    }

    /*
    * This register page is used for manager to register a employee account
    * */
    @RequestMapping(value= "/registration",method= RequestMethod.GET)
    public String register(@ModelAttribute("message") final String message, Model model) {
        model.addAttribute("message",message);
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

    @RequestMapping(value = "/register",method = POST)
    public String save(@Valid User user, @Valid int restaurant_id, RedirectAttributes redir){
        final SimpleGrantedAuthority AUTHORITY_MANAGER = new SimpleGrantedAuthority("MANAGER");
        final SimpleGrantedAuthority AUTHORITY_ADMIN = new SimpleGrantedAuthority("ADMIN");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User me = (org.springframework.security.core.userdetails.User)auth.getPrincipal();

        user.setRestaurantId(restaurant_id);
        User userExists = userService.findByUsername(user.getUsername());
        if(userExists !=null){
            redir.addFlashAttribute("message"," This username is already registered!");
        }else{
            userService.saveUser(user);
            User myUser = userService.findByUsername(user.getUsername());
            if(me.getAuthorities().contains(AUTHORITY_MANAGER)){
                userService.setUserRole(myUser.getId(),4); //4->employee
                redir.addFlashAttribute("message"," Successfully created new employee!");
            }else if(me.getAuthorities().contains(AUTHORITY_ADMIN)){
                userService.setUserRole(myUser.getId(),3); //3->manager
                redir.addFlashAttribute("message"," Successfully created new manager!");
            }

        }
        if(me.getAuthorities().contains(AUTHORITY_ADMIN)){
            return "redirect:/admin/registration";
        }else{  // else will be manager
            return "redirect:/registration";
        }

    }
}
