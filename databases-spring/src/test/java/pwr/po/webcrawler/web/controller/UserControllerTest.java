package pwr.po.webcrawler.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import parser.WebParser;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.controller.UserController;
import pwr.po.webcrawler.web.dto.UserDTO;
import pwr.po.webcrawler.web.mapper.UserMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userControllerMock = new UserController();

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;

    InputStream parserInput;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerMock).build();
        parserInput = this.getClass().getClassLoader().getResourceAsStream("ParserTest.html");
    }

    @Test
    public void Parser() throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(parserInput, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();

        } catch (IOException e) {}
        String content = contentBuilder.toString();

        WebParser wp = new WebParser();
        List<String> parse = wp.parser(content);

        List<String> links = new ArrayList<String>();
        links.add("http://www.zsk.iiar.pwr.wroc.pl/zsk/dyd/intinz/ptm/ptmlab/");
        links.add("http://www.zsk.iiar.pwr.wroc.pl/zsk/dyd/intinz/uc/");
        links.add("http://www.zsk.iiar.pwr.wroc.pl/zsk_ftp/cpld");
        links.add("http://www.xilinx.com/support/documentation/data_sheets/ds054.pdf");
        links.add("http://www.computer-engineering.org/ps2protocol/");
        links.add("http://www.zsk.iiar.pwr.wroc.pl/zsk_ftp/fpga/");
        Assert.assertThat(parse, is(links));
    }

    @Test
    public void getUsersList_UsersExist() throws Exception {
        User first = new User();
        first.setFirstName("Pierwszy");
        first.setLastName("User");
        first.setUsername("userPierwszy");

        User second = new User();
        second.setFirstName("Drugi");
        second.setLastName("User");
        second.setUsername("userDrugi");
        UserDTO firstDTO = UserMapper.map(first);
        UserDTO secondDTO = UserMapper.map(second);

        when(userService.getAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("userPierwszy")))
                .andExpect(jsonPath("$[0].firstName", is("Pierwszy")))
                .andExpect(jsonPath("$[0].lastName", is("User")))
                .andExpect(jsonPath("$[1].username", is("userDrugi")))
                .andExpect(jsonPath("$[1].firstName", is("Drugi")))
                .andExpect(jsonPath("$[1].lastName", is("User")));
    }

    @Test
    public void getUsersList_UsersNotExist() throws Exception {
        when(userService.getAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getUser_UserNotExist() throws Exception {

            mockMvc.perform(get("/user/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
    }

    @Test
    public void getUser_UserExist() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");
        user.setPassword("pass");

        when(userService.getUser(1)).thenReturn(user);
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$username", is("userPierwszy")))
                .andExpect(jsonPath("$firstName", is("Pierwszy")))
                .andExpect(jsonPath("$lastName", is("User")));
    }
    @Test
    public void activateUser_UserExist() throws Exception {
        User user = new User();
        user.setToken("token");
        when(userService.getUserByToken("token")).thenReturn(user);


        mockMvc.perform(put("/user/activate/"+user.getToken()))
                .andExpect(status().isOk());



    }


    @Test
    public void activateUser_UserNot_Exist() throws Exception{
        User user = new User();
        user.setToken("Token");
        when(userService.getUserByToken("tokenek")).thenReturn(user);
        mockMvc.perform(put("/user/activate/"+user.getToken()))
                .andExpect(status().isNotFound());

    }
    @Test
    public void save_UserExist() throws Exception {

        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("Password"));
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);
        when(userService.getUser(user.getId())).thenReturn(user);
        when(userService.getUser(user.getUsername())).thenReturn(user);
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);

        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void save_UserNotExist() throws Exception {
        try {
            User user = new User();
            user.setFirstName("Pierwszy");
            user.setLastName("User");
            user.setUsername("userPierwszy");
            user.setEmail("a@a.com");
            user.setPassword("pass");

            UserDTO dto = UserMapper.map(user);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dto);

            mockMvc.perform(put("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update_UserExist() throws Exception {

        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("Password"));
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        when(userService.getUser(1)).thenReturn(user);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void update_UserNotExist() throws Exception {

        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");

        user.setPassword("pass");
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);


        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void update_EmailConflict() throws Exception {

        User user = new User();
        user.setEmail("a@a.com");
        user.setId(1);
        user.setPassword("pass");
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("a@a.com");

        when(userService.getUserByEmail("a@a.com")).thenReturn(user2);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void delete_UserNotExist() throws Exception {

        User user = new User();
        user.setId(1);
        user.setPassword("pass");
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(delete("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void delete_UserExist() throws Exception {

        User user = new User();
        user.setId(1);
        user.setPassword("pass");
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        when(userService.getUser(1)).thenReturn(user);

        mockMvc.perform(delete("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
}
