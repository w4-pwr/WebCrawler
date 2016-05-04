package pwr.po.webcrawler.web.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartResolver;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;

import javax.servlet.ServletContext;
import java.io.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
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

    @InjectMocks
    private AvatarController avatarControllerMock = new AvatarController();

    @Autowired
    private MultipartResolver multipartResolver;

    @Mock
    UserService userService;

    @Mock
    ServletContext servletContext;

    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;

    File defaultFile;

    File file1;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(avatarControllerMock).build();
        defaultFile = new File(this.getClass().getResource("/default_avatar.jpg").getFile());
        file1 = new File(this.getClass().getResource("/avatar/1.jpg").getFile());
        MockitoAnnotations.initMocks(this);
    }

    //region Wysłanie pliku

    @Test
    public void testUpdateFile_UserExists() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("1");
        when(userService.getUser(user.getId())).thenReturn(user);
        when(userService.getUser(user.getUsername())).thenReturn(user);

        MockMultipartFile multipart = new MockMultipartFile("file", new FileInputStream(file1));

        mockMvc.perform(fileUpload("/user/" + user.getId() + "/avatar").file(multipart))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateFile_NoUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("1");

        when(servletContext.getRealPath("/")).thenReturn("");
        MockMultipartFile multipart = new MockMultipartFile("file", new FileInputStream(file1));

        mockMvc.perform(fileUpload("/user/" + user.getId() + "/avatar").file(multipart))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateFile_Exception() throws Exception {
        /*User user = new User();
        user.setId(1);
        user.setUsername("1");

        when(userService.getUser(user.getId())).thenThrow(new NullPointerException());
        when(servletContext.getRealPath("/")).thenReturn("");
        MockMultipartFile multipart = new MockMultipartFile("file", new FileInputStream(file1));

        mockMvc.perform(fileUpload("/user/" + user.getId() + "/avatar").file(multipart))
                .andExpect(status().isConflict()); */
    }

    @Test
    public void testUpdateFile_NoFile() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("1");

        when(servletContext.getRealPath("/")).thenReturn("");
        MockMultipartFile multipart = new MockMultipartFile("file", new byte[0]);
        mockMvc.perform(fileUpload("/user/" + user.getId() + "/avatar").file(multipart))
                .andExpect(status().isNoContent());
    }
    //endregion

    //region Pobranie pliku

    @Test
    public void testGetFile_UserExists() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("1");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);

        when(userService.getUser(user.getId())).thenReturn(user);
        when(userService.getUser(user.getUsername())).thenReturn(user);

        MockMultipartFile multipart = new MockMultipartFile("file", new FileInputStream(file1));

        mockMvc.perform(fileUpload("/user/" + user.getId() + "/avatar").file(multipart))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/" + user.getId() + "/avatar"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(file1))));
    }

    @Test
    public void testGetFile_UserExists_NoAvatar() throws Exception {
        User user = new User();
        user.setFirstName("Pierwszy");
        user.setLastName("User");
        user.setUsername("5");
        user.setEmail("a@a.com");
        user.setPassword("pass");
        user.setId(1);

        when(userService.getUser(user.getUsername())).thenReturn(user);
        when(userService.getUser(user.getId())).thenReturn(user);

        mockMvc.perform(get("/user/" + user.getId() + "/avatar"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(defaultFile))));

    }

    @Test
    public void testGetFile_NoUser() throws Exception {
        User user = new User();
        user.setUsername("999");
        user.setUsername("NonExistentUser");

        mockMvc.perform(get("/user/" + user.getId() + "/avatar"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .bytes(IOUtils.toByteArray(new FileInputStream(defaultFile))));

    }
//endregion
}