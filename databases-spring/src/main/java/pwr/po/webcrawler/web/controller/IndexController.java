package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pwr.po.webcrawler.model.User;
import pwr.po.webcrawler.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public String index() {
        service.save(new User("userName","userFirstName","LastName"));
        return "<b>Hello fdfdsfds</b>";
    }


}
