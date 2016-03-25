package pwr.po.webcrawler.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.repository.user.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService ,UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void save(User u) {
        userRepository.save(u);
    }

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);

        if (user != null) {
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
