package com.endava.spring.controller;

import com.endava.spring.model.User;
import com.endava.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
@Controller
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginPage(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String executeLogin(@ModelAttribute("user") User user){
        try{
            System.out.println(user.getUsername() + "--------" + user.getPassword());
            boolean isValidUser = userService.isValidUser(user.getUsername(), user.getPassword());
            if(isValidUser){
                return "successPage";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "invalidPage";
    }
}
