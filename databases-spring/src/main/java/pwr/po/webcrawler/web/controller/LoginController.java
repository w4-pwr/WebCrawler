package pwr.po.webcrawler.web.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(){
        return "success";
    }

    @RequestMapping(value = "login/{username}/{password}", method = RequestMethod.GET)
    public String login(@PathVariable String username, @PathVariable String password)
            throws ServletException {
        User user = null;
        if (username != null ) {
            user = userService.getUser(username.toLowerCase());
            if  ( user == null){
                throw new ServletException("Invalid login");}
        }
         //TODO Authenticate user

        return Jwts.builder().setSubject(username)
                .claim("role", user.getRole()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    }


    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
