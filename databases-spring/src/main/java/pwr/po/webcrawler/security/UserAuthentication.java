package pwr.po.webcrawler.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import pwr.po.webcrawler.model.user.User;

import java.util.Collection;

/**
 * Created by Rafal on 2016-03-23.
 *
 */
public class UserAuthentication implements Authentication{

    private  final User user;
    private boolean authenticated = true;
    public UserAuthentication(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
