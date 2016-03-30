package pwr.po.webcrawler.model.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Rafal on 2016-03-30.
 */
public enum UserRole implements GrantedAuthority {

    ADMIN,USER;

    @Override
    public String getAuthority() {
        return name();
    }

}
