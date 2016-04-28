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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Adam on 2016-04-16.
 */

@RestController
@RequestMapping(value = "/user/{id}/avatar")
public class AvatarController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> updateFile(@PathVariable long id, @RequestBody MultipartFile file, HttpServletRequest request) {
        File dir = new File(( servletContext.getRealPath("/") == null ? getClass().getProtectionDomain().getCodeSource().getLocation().getPath() : servletContext.getRealPath("/")) + "avatar");
        User user = null;
        if (!dir.exists()) dir.mkdirs();
        if (!file.isEmpty()) {
            try {
                user = userService.getUser(id);
                if (user == null)
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                File outFile = new File(path(user.getUsername()));
                System.out.println(outFile.toString());
                outFile.createNewFile();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(outFile, false));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                user.setProfileImage(request.getRequestURL().toString());
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
    public byte[] getFile(@PathVariable long id, HttpServletResponse response) throws Exception {
        InputStream in;
        User user = userService.getUser(id);

        if (user != null && userFileExists(user.getUsername())) {
            in = new FileInputStream(new File(path(user.getUsername())));
        } else {
            in = this.getClass().getClassLoader().getResourceAsStream("default_avatar.jpg");
        }

        return IOUtils.toByteArray(in);
    }

    public boolean userFileExists(String username) {
        File avatar = new File(path(username));
        return avatar.exists();
    }

    String path(String username) {
        String realPath = servletContext.getRealPath("/") == null ? getClass().getProtectionDomain().getCodeSource().getLocation().getPath() : servletContext.getRealPath("/");
        return realPath + System.getProperty("file.separator") + "avatar" + System.getProperty("file.separator") + username + ".jpg";
    }
}
