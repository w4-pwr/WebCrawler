package pwr.po.webrawler.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.controller.UserController;
import pwr.po.webcrawler.web.dto.UserDTO;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Rafał Niedźwiecki on 06.04.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userControllerMock = new UserController();

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;


    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerMock).build();
    }

    @Test
    public void getUsersList_UsersExist() throws Exception
    {
        User first = new User();
        first.setFirstName("Pierwszy");
        first.setLastName("User");
        first.setUsername("userPierwszy");

        User second = new User();
        second.setFirstName("Drugi");
        second.setLastName("User");
        second.setUsername("userDrugi");
        UserDTO firstDTO = new UserDTO(first);
        UserDTO secondDTO = new UserDTO(second);

        when(userService.getAll()).thenReturn(Arrays.asList(first,second));

        mockMvc.perform(get("/user/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].userName",is("userPierwszy")))
                .andExpect(jsonPath("$[0].firstName",is("Pierwszy")))
                .andExpect(jsonPath("$[0].lastName",is("User")))
                .andExpect(jsonPath("$[1].userName",is("userDrugi")))
                .andExpect(jsonPath("$[1].firstName",is("Drugi")))
                .andExpect(jsonPath("$[1].lastName",is("User")));
    }

    @Test
    public void getUsersList_UsersNotExist() throws Exception
    {
        when(userService.getAll()).thenReturn(Arrays.asList());
        mockMvc.perform(get("/user/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void changeUserPassword_UserNotExist() throws Exception
    {
        when(userService.getUser("userPierwszy")).thenReturn(null);

        mockMvc.perform(post("/user/change_password")
                .param("userName","userPierwszy")
                .param("oldPassword","Password")
                .param("newPassword","NewPassword")
                .param("matchNewPassword","NewPass"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed: user does not exist"));
    }

    @Test
    public void changeUserPassword_PasswordsArentSame() throws Exception
    {
        User first = new User();
        first.setFirstName("Pierwszy");
        first.setLastName("User");
        first.setUsername("userPierwszy");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        first.setPassword(encoder.encode("Password"));

        when(userService.getUser("userPierwszy")).thenReturn(first);


        mockMvc.perform(post("/user/change_password")


                .param("userName","userPierwszy")
                .param("oldPassword","Password")
                .param("newPassword","NewPassword")
                .param("matchNewPassword","NewPass"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed: passwords are not the same"));
    }

   @Test
    public void changeUserPassword_OldPasswordIsWrong() throws Exception
    {
        User first = new User();
        first.setFirstName("Pierwszy");
        first.setLastName("User");
        first.setUsername("userPierwszy");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        first.setPassword(encoder.encode("Password"));

        when(userService.getUser("userPierwszy")).thenReturn(first);


        mockMvc.perform(post("/user/change_password")


                .param("userName","userPierwszy")
                .param("oldPassword","Pass")
                .param("newPassword","NewPass")
                .param("matchNewPassword","NewPas0s"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed: old password is wrong"));
    }

    @Test
    public void changeUserPassword_Success() throws Exception
    {
        User first = new User();
        first.setFirstName("Pierwszy");
        first.setLastName("User");
        first.setUsername("userPierwszy");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        first.setPassword(encoder.encode("Password"));

        when(userService.getUser("userPierwszy")).thenReturn(first);

        mockMvc.perform(post("/user/change_password")
                .param("userName","userPierwszy")
                .param("oldPassword","Password")
                .param("newPassword","NewPass")
                .param("matchNewPassword","NewPass"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success : password was changed"));
    }

    @Test
    public void saveNewUser_UserExist() throws Exception
    {

        User first = new User();
        first.setFirstName("Pierwszy");
        first.setLastName("User");
        first.setUsername("userPierwszy");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        first.setPassword(encoder.encode("Password"));

        when(userService.getUser("userPierwszy")).thenReturn(first);

        mockMvc.perform(post("/user/save")
                .param("userName","userPierwszy")
                .param("firstName","firstname")
                .param("lastName","lastname")
                .param("password","Password")
                .param("matchPassword","Password")
                .param("email","email@email.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed: User exist"));
    }

    @Test
    public void saveNewUser_PasswordsArentSame() throws Exception
    {
        when(userService.getUser("userPierwszy")).thenReturn(null);
        mockMvc.perform(post("/user/save")
                .param("userName","userPierwszy")
                .param("firstName","firstname")
                .param("lastName","lastname")
                .param("password","Password")
                .param("matchPassword","Pass")
                .param("email","email@email.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed: passwords aren't the same"));
    }

    @Test
    public void saveNewUser_Success() throws Exception
    {
        when(userService.getUser("userPierwszy")).thenReturn(null);
        mockMvc.perform(post("/user/save")
                .param("userName","userPierwszy")
                .param("firstName","firstname")
                .param("lastName","lastname")
                .param("password","Password")
                .param("matchPassword","Password")
                .param("email","email@email.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success : User saved"));
    }
}
