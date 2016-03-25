package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

      @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public String index() {
          System.out.println("rafal");
          User user = new User();
          user.setUsername("Test");
          user.setFirstName("userFirstName");
          user.setLastName("userLastName");

          userService.save(user);
        return "<b>Strona domowa</b>";
    }



}
