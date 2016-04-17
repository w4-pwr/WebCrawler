package pwr.po.webcrawler.web.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.UserDTO;

import javax.servlet.ServletContext;
import java.io.*;

/**
 * Created by Adam on 2016-04-16.
 */

@RestController
@RequestMapping(value = "avatar")
public class AvatarController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> updateFile(@RequestParam String username, @RequestBody MultipartFile file) {
        File dir = new File(servletContext.getRealPath("/") + "avatar");
        if (!dir.exists()) dir.mkdir();
        if (!file.isEmpty()) {
            try {
                if (userService.getUser(username) == null)
                    return new ResponseEntity<String>(HttpStatus.CONFLICT);
                if (!new File("avatar").exists())
                    new File("avatar").mkdir();
                File outFile = new File(servletContext.getRealPath("/")
                        + "avatar"
                        + System.getProperty("file.separator")
                        + username
                        + ".jpg");

                outFile.createNewFile();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(outFile, false));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                User user = userService.getUser(username);
                user.setProfileImage(outFile.getAbsolutePath());
                userService.save(user);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getFile(@RequestBody UserDTO dto) throws IOException {
        InputStream in;
        User user = userService.getUser(dto.getUsername());
        if (user != null) {
            if (userFileExists(user.getUsername())) {
                in = new FileInputStream(new File(servletContext.getRealPath("/") + "avatar" + System.getProperty("file.separator") + dto.getUsername() + ".jpg"));
            } else in = this.getClass().getClassLoader().getResourceAsStream("default_avatar.jpg");

        } else {
            in = this.getClass().getClassLoader().getResourceAsStream("default_avatar.jpg");
        }
        return IOUtils.toByteArray(in);
    }


    @RequestMapping(value ="by_name", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getFileByUsername(@RequestParam String username) throws IOException {
        InputStream in;
        User user = userService.getUser(username);
        if (user != null) {
            if (userFileExists(user.getUsername())) {
                in = new FileInputStream(new File(servletContext.getRealPath("/") + "avatar" + System.getProperty("file.separator") + username + ".jpg"));
            } else in = this.getClass().getClassLoader().getResourceAsStream("default_avatar.jpg");

        } else {
            in = this.getClass().getClassLoader().getResourceAsStream("default_avatar.jpg");
        }
        return IOUtils.toByteArray(in);
    }


    public boolean userFileExists(String username) {
        File avatar = new File(servletContext.getRealPath("/") + "avatar" + System.getProperty("file.separator") + username + ".jpg");
        return avatar.exists();
    }
}
