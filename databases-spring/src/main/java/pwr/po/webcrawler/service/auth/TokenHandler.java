package pwr.po.webcrawler.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;

/**
 * Created by Rafal on 2016-03-23.
 */

@Service
public class TokenHandler {


    @Autowired
    private UserService userService;


    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (User) userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }
}
