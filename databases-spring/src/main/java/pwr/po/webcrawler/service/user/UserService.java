package pwr.po.webcrawler.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.model.user.User;

import java.util.List;

public interface UserService extends UserDetailsService {

     List<User> getAll();

     void save(User u) ;

     User getUser(long id);

     User getUser(String username);

     User getUserByEmail(String email);

     void deleteUser(long id);


}
