package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by guang on 2017/4/3.
 */

@Controller
public class UserController {
    @RequestMapping(value= "/login",method=GET)
    public String login() {
        return "login";
    }

}
