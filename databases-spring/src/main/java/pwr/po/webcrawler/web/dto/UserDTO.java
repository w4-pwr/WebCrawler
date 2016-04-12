package pwr.po.webcrawler.web.dto;

import pwr.po.webcrawler.model.user.User;

/**
 * Created by Rafal on 2016-03-24.
 *
 */
public class UserDTO {

    public String userName;

    public String firstName;

    public String lastName;

    public UserDTO(User user) {
        this.userName = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();

    }
}
