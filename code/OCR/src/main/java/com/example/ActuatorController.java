package com.example;

import com.example.entities.Restaurant;
import com.example.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by lenna on 4/25/2017.
 * controller to return error messages or server health information
 */
@Controller
public class ActuatorController implements ErrorController{

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("/error")
    public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("errorNumber",response.getStatus());
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map errors = errorAttributes.getErrorAttributes(requestAttributes,true);
        model.addAttribute("errorType",errors.get("error"));
        model.addAttribute("stackTrace",errors.get("message"));
       //model.addAttribute("errorResponse",getErrorAttributes(request,true));
        return "error";
    }

    @RequestMapping("/")
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Restaurant> list =  restaurantService.findAll();
        model.addAttribute("restaurants",list);
        if(auth.getPrincipal().equals("anonymousUser")){
            model.addAttribute("logged",false);
        }else{
            model.addAttribute("logged",true);
        }
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
