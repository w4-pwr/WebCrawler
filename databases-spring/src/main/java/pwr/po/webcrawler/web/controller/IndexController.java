package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.model.user.UserRole;
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

    @RequestMapping(value = "rafal", method = GET)
    public void saveFakeData() {

        User user = new User();
        user.setUsername("user");
        user.setFirstName("userFirstName");
        user.setLastName("userLastName");
        user.setRole(UserRole.ADMIN);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("password"));
        userService.save(user);
    }

}
