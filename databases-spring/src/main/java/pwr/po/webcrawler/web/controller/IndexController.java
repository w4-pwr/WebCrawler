package pwr.po.webcrawler.web.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pwr.po.webcrawler.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "/login", method = GET)
    @ResponseBody
    public String loginPage() {
       return "login_temp";
    }

}
