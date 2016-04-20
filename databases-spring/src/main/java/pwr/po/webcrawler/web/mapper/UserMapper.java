package pwr.po.webcrawler.web.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.model.user.UserRole;
import pwr.po.webcrawler.web.dto.UserDTO;


public class UserMapper {

    public static User map(UserDTO dto) {
        if(dto == null) return null;
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setId(dto.getId());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setRole(UserRole.USER);
        user.setRegistrationDate(dto.getRegistrationDate());
        if (dto.getPassword() != null)
            user.setPassword(encoder.encode(dto.getPassword()));

        return user;
    }

    public static UserDTO map(User user) {
        if(user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setRegistrationDate(user.getRegistrationDate());
        return dto;
    }
}