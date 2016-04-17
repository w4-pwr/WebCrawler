package pwr.po.webcrawler.web.controller;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.UserDTO;
import pwr.po.webcrawler.web.mapper.UserMapper;
import org.apache.commons.io.IOUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
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

 /*   @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> uploadFile(@RequestParam UserDTO dto, @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                File outFile = new File("/avatar/" + dto.getUsername() + ".jpg");
                if (outFile.exists()) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(outFile));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

                User user = UserMapper.map(dto);
                user.setProfileImage(outFile.getAbsolutePath());
                userService.save(user);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> updateFile(@RequestBody UserDTO dto, @RequestBody MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                File outFile = new File("/avatar/" + dto.getUsername() + ".jpg");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(outFile, false));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

                User user = UserMapper.map(dto);
                user.setProfileImage(outFile.getAbsolutePath());
                userService.save(user);
            } catch (Exception e) {
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
        System.out.println("getFile "+dto.getUsername());
        File avatar = new File("/avatar/" + dto.getUsername() + ".jpg");
        if (!avatar.exists())
            System.out.println("no file");
        //       return new FileSystemResource(new File("/avatar/default.jpg"));
     //   return new FileSystemResource(avatar);


        InputStream in = servletContext.getResourceAsStream(avatar.exists()? "/avatar/" + dto.getUsername() + ".jpg" : "/avatar/default.jpg");
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(value = {"username"},method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getFileByUsername(@RequestParam String username) throws IOException {
        System.out.println("getFile "+username);
        File avatar = new File("/avatar/" + username + ".jpg");
        if (!avatar.exists())
            System.out.println("no file");
        //       return new FileSystemResource(new File("/avatar/default.jpg"));
        //   return new FileSystemResource(avatar);
        System.out.println(servletContext.getContextPath());
        InputStream in = new FileInputStream(avatar.exists()? avatar : new File("/avatar/default.jpg"));
        return IOUtils.toByteArray(in);
    }
}
