package com.example;

import com.example.entities.Menu;
import com.example.entities.Order;
import com.example.entities.OrderRepository;
import com.example.entities.Restaurant;
import com.example.services.FoodService;
import com.example.services.MenuService;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guang on 2017/4/28.
 */
@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping(value="/restaurant/{name}/createOrder", method= RequestMethod.POST) //accessible by user with role "manager"
    public String createOrder(@PathVariable String name,@Valid String[] foods, @Valid String[] numbers,@Valid int tableId
            , RedirectAttributes redir){
        String order = "Table id: "+tableId;
        ArrayList<String> list = new ArrayList<String>();
        for (String s : numbers) {
            if (!s.isEmpty())
                list.add(s);
        }
        for(int i=0;i<foods.length;i++){
            order += " food id: " + foods[i] + " number: "+list.get(i);
        }
        log.info(order);
        redir.addFlashAttribute("redir",order);
        Order newOrder = new Order();
        newOrder.setContent(order);
        newOrder.setTableId(tableId);
        orderRepository.save(newOrder);
        return "redirect:/restaurant/"+name+"/order";
    }

    @RequestMapping(value="/restaurant/{name}/order", method= RequestMethod.GET) //accessible by user with role "manager"
    public String order(@PathVariable String name, @ModelAttribute("redir") final String order, Model model){
        Restaurant rest = restaurantService.findRestaurantByName(name);
        if(rest == null){
            return "redirect:/error";         //if this manager is not bounded to this restaurant.
        }else{
            model.addAttribute("message",order);
            return "order";
        }
    }

}
