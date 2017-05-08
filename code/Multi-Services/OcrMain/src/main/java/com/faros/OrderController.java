package com.faros;

import com.faros.services.OrderService;
import com.faros.services.RestaurantService;
import com.faros.services.UserService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

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
    private OrderService orderService;

    @RequestMapping(value="/restaurant/{name}/createOrder", method= RequestMethod.POST) //accessible by user with role "manager"
    public String createOrder(@PathVariable String name, @Valid String[] foods, @Valid String[] numbers, @Valid int tableId
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
        JSONObject newOrder = new JSONObject();
        newOrder.put("content",order);
        newOrder.put("tableId",tableId);
        orderService.save(newOrder);
        return "redirect:/restaurant/"+name+"/order";
    }

    @RequestMapping(value="/restaurant/{name}/order", method= RequestMethod.GET) //accessible by user with role "manager"
    public String order(@PathVariable String name, @ModelAttribute("redir") final String order, Model model){
        JSONObject rest = restaurantService.findRestaurantByName(name);
        if(rest == null){
            return "redirect:/error";         //if this manager is not bounded to this restaurant.
        }else{
            model.addAttribute("message",order);
            return "order";
        }
    }

}
