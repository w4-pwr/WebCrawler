package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pwr.po.webcrawler.model.User;
import pwr.po.webcrawler.service.UserService;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/getall", method = GET)
    public List<User> get() {
        return userService.getAll();
    }

    @RequestMapping(value="/user/{id}", method=GET)
    public @ResponseBody User getUser(@PathVariable Long id){

        return userService.getUser(id);
    }

    //FIXME URI encoding on @ symbol
    @RequestMapping(value="user/save/{username}/{firstName}/{lastName}/{email}")
    public long saveUser(@PathVariable String username, @PathVariable String firstName,@PathVariable String lastName, @PathVariable String email){
        User user = new User(username,firstName,lastName);
        user.setRegistrationDate(new Date());
        user.setEmail(email);
        userService.save(user);
        return userService.getUser(username).getId();
    }


    @RequestMapping(value="user/delete/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "success";
    }

    @RequestMapping(value="user/changeemail/{id}/{newEmail}")
    public String changeEmail(@PathVariable long id ,@PathVariable String email){
        User user = userService.getUser(id);
        user.setEmail(email);
        userService.save(user);
        return "success";
    }
}
