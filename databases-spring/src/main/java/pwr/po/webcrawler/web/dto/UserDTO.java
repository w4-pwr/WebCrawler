package pwr.po.webcrawler.web.dto;

import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.model.user.UserRole;

import java.util.Date;

/**
 * Created by Rafal on 2016-03-24.
 *
 */

@Getter
@Setter
public class UserDTO {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private Date registrationDate;

    private String email;

    private UserRole role;

}
