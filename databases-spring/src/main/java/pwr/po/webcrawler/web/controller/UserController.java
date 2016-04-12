package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    public @ResponseBody User getUser(@PathVariable Long id, final HttpServletRequest request, Principal principal){
        return userService.getUser(id);
    }

    @RequestMapping(value="save")
    public @ResponseBody String saveUser(@RequestParam("userName") String username ,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("password") String password,
                         @RequestParam("matchPassword")String matchPassword,
                         @RequestParam("email") String email){

        if(userService.getUser(username) != null){
            return "Failed: User exist";
        }
        if(!password.equals(matchPassword))
        {
            return "Failed: passwords aren't the same";
        }
        User user = new User(username.toLowerCase(),firstName,lastName);
        user.setRegistrationDate(new Date());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setRole(UserRole.USER);
        userService.save(user);
        return "Success : User saved";
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
    @RequestMapping(value="changefirstname/{id}/{newFirstName}")
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
    @RequestMapping(value="change_password")
    public @ResponseBody String changeUserPassword(@RequestParam("userName") String username,
                                                 @RequestParam("oldPassword")String oldPassword,
                                                 @RequestParam("newPassword")String newPassword,
                                                 @RequestParam("matchNewPassword")String matchNewPassword)
    {
        User user = userService.getUser(username);
        if(user==null)
            return "Failed: user does not exist";

        if(!User.PASSWORD_ENCODER.matches(oldPassword,user.getPassword()))
        {
            return "Failed: old password is wrong";

        }
        if(!newPassword.equals(matchNewPassword))
        {
            return "Failed: passwords are not the same";
        }
            user.setPassword(newPassword);
            userService.save(user);
        return "Success : password was changed";
    }
}
