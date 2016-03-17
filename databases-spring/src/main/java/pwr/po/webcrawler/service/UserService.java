package pwr.po.webcrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.po.webcrawler.model.Preferences;
import pwr.po.webcrawler.model.User;
import pwr.po.webcrawler.repository.PreferencesRepository;
import pwr.po.webcrawler.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

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
}
