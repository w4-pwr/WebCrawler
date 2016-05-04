package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.UserDTO;
import pwr.po.webcrawler.web.mapper.UserMapper;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public List<UserDTO> get() {
        List<User> list = userService.getAll();
        List<UserDTO> result = new ArrayList<>();
        for (User user : list) {
            result.add(UserMapper.map(user));
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public UserDTO getUser(@PathVariable Long id) {User user = userService.getUser(id);
        if(user == null){

        }
        return UserMapper.map(user);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<String> saveUser(@RequestBody UserDTO dto) {

        if (userService.getUser(dto.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (userService.getUserByEmail(dto.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        dto.setRegistrationDate(new Date());

        byte[] b = new byte[20];
        new Random().nextBytes(b);
        dto.setToken((DigestUtils.md5DigestAsHex(b)));

        userService.save(UserMapper.map(dto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = PUT, value = "/activate/{token}")
    public ResponseEntity<String> activateUser(@PathVariable  String token) {
        User user = userService.getUserByToken(token);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setEnabled(true);
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<String> update(@RequestBody UserDTO dto) {
        if( userService.getUser(dto.getId()) == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User u = userService.getUserByEmail(dto.getEmail());
        if ( u != null && u.getId() != dto.getId() && u.getEmail().equals(dto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.save(UserMapper.map(dto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO user) {
        if( userService.getUser(user.getId()) == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
