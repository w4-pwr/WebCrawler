package pwr.po.webcrawler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.auth.TokenAuthenticationService;
import pwr.po.webcrawler.service.user.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rafal on 2016-03-23.
 */
@Component
public class StatelessLoginFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    private TokenAuthenticationService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        setAuthenticationManager(authenticationManager);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("StatelessLoginFilter.successfulAuthentication");
        // Lookup the complete User object from the database and create an Authentication for it
        final User authenticatedUser = (User) userService.loadUserByUsername(authResult.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

        // Add the custom token as HTTP header to the response
        tokenService.addAuthentication(response, userAuthentication);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("StatelessLoginFilter.attemptAuthentication");

        try {
//            UserRequest user = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);
//            return  getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            //
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            redirect(response);
            return null;
        }
    }

    private void redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/auth/unauthorized");
        } catch (IOException e1) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
