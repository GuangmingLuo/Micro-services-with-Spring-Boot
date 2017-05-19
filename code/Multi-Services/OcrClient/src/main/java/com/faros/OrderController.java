package com.faros;

import com.faros.services.FoodService;
import com.faros.services.OrderService;
import com.faros.services.RestaurantService;
import com.faros.services.UserService;
import my.faros.model.Food;
import my.faros.model.Order;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private OrderService orderService;
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/restaurant/{name}/createOrder", method = RequestMethod.POST)
    public String createOrder(@PathVariable String name, @Valid String[] foods, @Valid String[] numbers, @Valid String tableId
            , RedirectAttributes redir) throws Exception {
        String order = "";
        ArrayList<String> list = new ArrayList<String>();
        for (String s : numbers) {
            if (!s.isEmpty() && !s.equals("0"))
                list.add(s);
        }
        for (int i = 0; i < foods.length; i++) {
            Food food = foodService.findFoodById(foods[i]);
            order += " name: " + food.getName() + " number: " + list.get(i);
        }
        log.info(order);
        redir.addFlashAttribute("content", order);
        redir.addFlashAttribute("tableId", tableId);
        return "redirect:/restaurant/" + name + "/order";
    }

    @RequestMapping(value = "/restaurant/saveOrder", method = RequestMethod.POST)
    public String saveOrder(@Valid String content,@Valid String restaurantName,@Valid int tableId,@Valid String comment){
        Order newOrder = new Order();
        newOrder.setContent(content);
        newOrder.setTableId(tableId);
        JSONObject rest = restaurantService.findRestaurantByName(restaurantName);
        newOrder.setRestaurantId(Integer.parseInt(rest.getAsString("id")));
        newOrder.setComments(comment);
        orderService.save(newOrder);
        return "redirect:/restaurant/"+ restaurantName;
    }
    @RequestMapping(value = "/restaurant/{name}/order", method = RequestMethod.GET)
    //accessible by user with role "manager"
    public String order(@PathVariable String name, @ModelAttribute("content") final String content,
                        @ModelAttribute("tableId") final String tableId, Model model) {
        JSONObject rest = restaurantService.findRestaurantByName(name);
        if (rest == null) {
            return "redirect:/error";         //if this manager is not bounded to this restaurant.
        } else {
            model.addAttribute("content", content);
            model.addAttribute("tableId", tableId);
            model.addAttribute("restaurantName", rest.getAsString("name"));
            return "order";
        }
    }

    @RequestMapping(value = "/restaurant/orderOverview", method = RequestMethod.GET)
    //accessible by user with role "manager"
    public String orderOverview(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        JSONObject userExists = userService.findByUsername(user.getUsername());
        JSONObject rest = restaurantService.findRestaurantById(Integer.parseInt(userExists.getAsString("restaurantId")));
        model.addAttribute("restaurantName", rest.getAsString("name"));
        List<JSONObject> orders =orderService.findAll(Integer.parseInt(rest.getAsString("id")));
        model.addAttribute("orders", orders);
        return "orderOverview";
    }

    @RequestMapping(value = "/restaurant/order/changeStatus", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    //accessible by user with role "manager"
    public void changeStatus(@Valid long id, String status) {
        log.info("Here comes in changeStatus:{} and {}",id,status);
        orderService.changeOrderStatus(id,status);
    }
}
