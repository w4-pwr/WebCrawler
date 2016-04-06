package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.model.user.UserRole;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value="user")
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getall", method = GET)
    public List<UserDTO> get() {
        List<User> list = userService.getAll();
        List<UserDTO> resultList = new LinkedList<>();
        for(User user : list){
            resultList.add(new UserDTO(user));
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="{id}", method=GET)
    public @ResponseBody
    User getUser(@PathVariable Long id, final HttpServletRequest request, Principal principal){
        return userService.getUser(id);
    }

    //FIXME URI encoding on @ symbol
    @RequestMapping(value="save")
    public @ResponseBody long saveUser(@RequestParam("username") String username ,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("password") String password,
                         @RequestParam("matchPassword")String matchPassword,
                         @RequestParam("email") String email){

        if(userService.getUser(username) != null){
            return -1;
        }
        if(!password.equals(matchPassword))
        {
            return -1;
        }
        User user = new User(username.toLowerCase(),firstName,lastName);
        user.setRegistrationDate(new Date());
        user.setPassword(password);//TODO BCrypt encoder
        user.setEmail(email);
        user.setRole(UserRole.USER);
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
    @RequestMapping(value="changefirstname/{id}/newFirstName}")
    public String changeFirstName(@PathVariable long id, @PathVariable String firstName) {
        User user = userService.getUser(id);
        user.setFirstName(firstName);
        userService.save(user);
        return "success";
    }
    @RequestMapping(value="changelastname/{id}/{newLastName)")
    public String changeLastName(@PathVariable long id, @PathVariable String lastName) {
        User user = userService.getUser(id);
        user.setLastName(lastName);
        userService.save(user);
        return "success";
    }
    @RequestMapping(value="changepassword/{id}/{newPassword)")
    public String changePassword(@PathVariable long id, @PathVariable String password) {
        User user = userService.getUser(id);
        user.setPassword(password);
        userService.save(user);
        return "success";
    }
    @RequestMapping(value="changeprofileimage/{id}/{newProfileImage})")
    public String changeProfileImage(@PathVariable long id, @PathVariable String profileImage) {
        User user = userService.getUser(id);
        user.setProfileImage(profileImage);
        userService.save(user);
        return "success";
    }
    @RequestMapping(value="editprofile/{id}")
    public String editProfile(@PathVariable long id, @PathVariable String firstname,
                              @PathVariable String lastname,@PathVariable String email,
                              @PathVariable String password, @PathVariable String profileImage){
        changeFirstName(id,firstname);
        changeLastName(id,lastname);
        changeEmail(id,email);
        changePassword(id,password);
        changeProfileImage(id,profileImage);
        return "success";
    }
}
