package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.po.webcrawler.model.User;
import pwr.po.webcrawler.service.UserService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = GET)
    public List<User> get() {
        return userService.getAll();
    }

    @RequestMapping(value = "/users/add", method = GET)
    public String add() {
        userService.save(new User("added new user", "test", "test"));
        return "success";
    }
}
