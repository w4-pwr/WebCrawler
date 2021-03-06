package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pwr.po.webcrawler.service.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public String index() {

        return "<b>Strona domowa</b>";
    }


}
