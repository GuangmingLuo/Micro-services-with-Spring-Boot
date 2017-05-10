package com.faros;

import com.faros.entities.User;
import com.faros.services.RestaurantService;
import com.faros.services.UserService;
import net.minidev.json.JSONObject;
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
            User userExists = userService.findByUsername(user.getUsername());
            JSONObject rest = restaurantService.findRestaurantById(userExists.getRestaurantId());
            return "redirect:/restaurant/"+rest.getAsString("name")+"/";
        }
        return "login";
    }

    /*
    * This register page is used for manager to register a employee account
    * */
    @RequestMapping(value= "/registration",method= RequestMethod.GET)
    public String register(@ModelAttribute("message") final String message, Model model) {
        model.addAttribute("message",message);
        List<JSONObject> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants",restaurants);
        return "register";
    }

    /*
    * This post function is shared by manager and admin (registration page)
    * */
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
        }else{  // else will be manager in this case
            return "redirect:/registration";
        }
    }
//    /*
//    * This is only used to create the super user/admin
//    * add .antMatchers("/admin/registration","/register").permitAll() in config
//    * */
//    public String save(@Valid User user, @Valid int restaurant_id, RedirectAttributes redir){
//        user.setRestaurantId(restaurant_id);
//        userService.saveUser(user);
//        User myUser = userService.findByUsername(user.getUsername());
//        userService.setUserRole(myUser.getId(),2); //2->admin
//        redir.addFlashAttribute("message"," Successfully created new admin!");
//        return "redirect:/admin/registration";
//    }


}
