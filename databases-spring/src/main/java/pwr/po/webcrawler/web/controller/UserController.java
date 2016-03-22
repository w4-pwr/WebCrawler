package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value="user")
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getall", method = GET)
    public List<User> get() {
        return userService.getAll();
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="{id}", method=GET)
    public @ResponseBody User getUser(@PathVariable Long id, final HttpServletRequest request, Principal principal){
        return userService.getUser(id);
    }

    //FIXME URI encoding on @ symbol
    @RequestMapping(value="save/{username}/{firstName}/{lastName}/{password}/{email}")
    public long saveUser(@PathVariable String username, @PathVariable String firstName,@PathVariable String lastName, @PathVariable String password, @PathVariable String email){
        if(userService.getUser(username) != null){
            return -1;
        }
        User user = new User(username.toLowerCase(),firstName,lastName);
        user.setRegistrationDate(new Date());
        user.setPassword(password);//TODO BCrypt encoder
        user.setEmail(email);
        user.setRole(new String []{"ROLE_USER"});
        userService.save(user);
        return userService.getUser(username.toLowerCase()).getId();
    }


    @RequestMapping(value="delete/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "success";
    }

    @RequestMapping(value="changeemail/{id}/{newEmail}")
    public String changeEmail(@PathVariable long id ,@PathVariable String email) {
        User user = userService.getUser(id);
        user.setEmail(email);
        userService.save(user);
        return "success";
    }
}
