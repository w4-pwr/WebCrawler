package pwr.po.webcrawler.service.user;

import pwr.po.webcrawler.model.user.User;

import java.util.List;

/**
 * Created by Rafal on 2016-03-22.
 */
public interface UserService {

    List<User> getAll();

    void save(User u);

    User getUser(long id);

    User getUser(String username);

    User getUserByEmail(String email);

    public void deleteUser(long id);

}
