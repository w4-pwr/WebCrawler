package pwr.po.webcrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.model.CurrentUser;
import pwr.po.webcrawler.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<CurrentUser> getAll() {
        return userRepository.findAll();
    }

    public void save(CurrentUser u) {
        userRepository.save(u);
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<CurrentUser> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()) {
            CurrentUser user = optionalUser.get();
            return new User(user.getUsername(), user.getPassword(),
                    AuthorityUtils.createAuthorityList(user.getRoles()));
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
