package com.faros;

import com.faros.entity.Order;
import com.faros.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by guang on 2017/5/11.
 */
@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private OrderService orderService;

    @RequestMapping
    public String info() {
        String info = "Available api under ip:84/api: <br />";
        info += "/status (To check if the server in on)<br />";
        info += "/orders?restaurantId=xxx <br />";
        info += "/addOrder (POST: @RequestBody Order o) <br />";
        return info;
    }

    /*
    * This api returns a list of food entities by a menu id
    * */
    @RequestMapping("/orders")
    public List<Order> orders(@RequestParam(value="restaurantId") int restaurantId){
        return orderService.findAllOrders(restaurantId);
    }

    /*
    * This api returns true if server is on
    * */
    @RequestMapping("/status")
    public boolean status(){
        return true;
    }

    /*
    * This api do a post to database
    * */
    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        Order result = orderService.addOrder(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/orderById",method = RequestMethod.GET)
    public Order getOrderById(@RequestParam(value="id") long id){
        return orderService.findOrderById(id);
    }

    @RequestMapping(value = "/saveOrder",method = RequestMethod.POST)
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        Order result = orderService.save(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
