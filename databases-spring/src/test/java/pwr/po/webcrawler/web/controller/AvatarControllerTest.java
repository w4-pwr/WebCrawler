package pwr.po.webcrawler.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartResolver;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.UserDTO;
import pwr.po.webcrawler.web.mapper.UserMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Adam on 2016-04-17.
 */

//@RunWith(MockitoJUnitRunner.class)
public class AvatarControllerTest {
/*
    @InjectMocks
    private AvatarController avatarControllerMock = new AvatarController();

    @Autowired
    private MultipartResolver multipartResolver;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;

    File defaultFile;

    File file1;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(avatarControllerMock).build();
        defaultFile = new File(getClass().getResource("/default_avatar.jpg").getFile());
        file1 = new File(getClass().getResource("/cow.jpg").getFile());
    }

    @Test
    public void testUpdateFile_UserExists() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        when(userService.getUser(user.getUsername())).thenReturn(user);


       mockMvc.perform(post("/avatar").param("username", user.getUsername())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(IOUtils.toByteArray(new FileInputStream(file1))))
                .andExpect(status().isOk());



    }

    @Test
    public void testUpdateFile_NoUser() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        mockMvc.perform(post("/avatar").param("username", user.getUsername())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(IOUtils.toByteArray(new FileInputStream(file1))))
                .andExpect(status().isConflict())
        ;
    }

    @Test
    public void testUpdateFile_Exception() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("userPierwszy");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        when(userService.getUser(user.getUsername())).thenThrow(new Exception());

        mockMvc.perform(post("/avatar").param("username", user.getUsername())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(IOUtils.toByteArray(new FileInputStream(file1))))
                .andExpect(status().isConflict())
        ;
    }

    @Test
    public void testUpdateFile_NoFile() throws Exception {

        mockMvc.perform(post("/avatar").param("username", "")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(new byte[0]))
                .andExpect(status().isNoContent());
    }


    // =====================================================================================


    @Test
    public void testGetFile_UserExists() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("cow");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);
        when(userService.getUser(user.getUsername()))
                .thenReturn(user);
        when(avatarControllerMock.userFileExists(user.getUsername()))
                .thenReturn(true);

        mockMvc.perform(get("/avatar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(file1))));

    }

    @Test
    public void testGetFile_UserExists_NoAvatar() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("cow");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        when(userService.getUser(user.getUsername())).thenReturn(user);
        when(avatarControllerMock.userFileExists(user.getUsername())).thenReturn(false);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(get("/avatar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(defaultFile))));
    }

    @Test
    public void testGetFile_NoUser() throws Exception {
        User user = new User();
        user.setUsername("NonExistentUser");
        UserDTO dto = UserMapper.map(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(get("/avatar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(defaultFile))));
    }


    // =====================================================================================


    @Test
    public void testGetFileByUsername_UserExists() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("cow");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        when(userService.getUser(user.getUsername())).thenReturn(user);
        when(avatarControllerMock.userFileExists(user.getUsername())).thenReturn(true);

        mockMvc.perform(get("/avatar").param("username", user.getUsername()))
                .andExpect(status().isOk()).andExpect(content()
                .bytes(IOUtils.toByteArray(new FileInputStream(file1))));

    }

    @Test
    public void testGetFileByUsername_UserExists_NoAvatar() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("cow");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);
        UserDTO dto = UserMapper.map(user);

        when(userService.getUser(user.getUsername())).thenReturn(user);
        when(avatarControllerMock.userFileExists(user.getUsername())).thenReturn(false);

        mockMvc.perform(get("/avatar").param("username", user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(defaultFile))));
    }

    @Test
    public void testGetFileByUsername_NoUser() throws Exception {
        mockMvc.perform(get("/avatar").param("username", "NonExistentUsername").contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(defaultFile))));
    }
*/
}