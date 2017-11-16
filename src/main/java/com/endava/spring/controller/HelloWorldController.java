package com.endava.spring.controller;

import com.endava.spring.model.User;
import com.endava.spring.service.SecurityService;
import com.endava.spring.service.UserService;
import com.endava.spring.validator.UserInputValidator;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
@Controller
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserInputValidator userInputValidator;

    @InitBinder
    public void initWebBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userInputValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            modelMap.addAttribute("username", authentication.getName());
            modelMap.addAttribute("user", userService.findByUserName(authentication.getName()));
        }
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String displayWelcomePage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            modelMap.addAttribute("username", authentication.getName());
            modelMap.addAttribute("user", userService.findByUserName(authentication.getName()));
        }
        return "welcome";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String executeLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayAdminPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            modelMap.addAttribute("username", authentication.getName());
        }
        return "admin";
    }

    @RequestMapping(value = "/moderator", method = RequestMethod.GET)
    public String displayModeratorPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            modelMap.addAttribute("username", authentication.getName());
        }
        return "moderator";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginPage(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("users", userService.listUsers());
        modelMap.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String executeLogin(@ModelAttribute("user") User user) {
        return "successPage";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String displayRegistrationPage(ModelMap modelMap) {
//        messageSource.getMessage("test.test", null, Locale.US);
        modelMap.addAttribute("user", new User());
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String executeRegistration(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        MultipartFile userImage = user.getUserImage();
        try {
            user.setImage(Base64.encode(userImage.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        userService.saveUser(user);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        return "redirect:/welcome";
    }

}
