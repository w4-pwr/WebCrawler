package pwr.po.webcrawler.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import pwr.po.webcrawler.model.user.User;

import java.util.List;

/**
 * Created by Rafal on 2016-03-23.
 */
public interface UserService extends UserDetailsService {



     List<User> getAll();

     void save(User u) ;

     User getUser(long id);

     User getUser(String username);

     User getUserByEmail(String email);

    public void deleteUser(long id);

}
