package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.auth.TokenAuthenticationService;
import pwr.po.webcrawler.service.user.UserService;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(){
        return new User("testUserName","userFirstName","lastName");
    }



}
