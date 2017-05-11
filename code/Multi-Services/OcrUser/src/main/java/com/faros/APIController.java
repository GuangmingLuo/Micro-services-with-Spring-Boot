package com.faros;

import com.faros.model.User;
import com.faros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by guang on 2017/5/11.
 */
@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String info() {
        String info = "Available api under ip:85/api: <br />";
        info += "/addUser (POST: @RequestBody User u) <br />";
        return info;
    }

    /*
    * This api do a post to database
    * */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User result = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
