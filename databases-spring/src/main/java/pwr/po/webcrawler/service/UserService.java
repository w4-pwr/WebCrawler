package pwr.po.webcrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.model.User;
import pwr.po.webcrawler.repository.UserRepository;

import java.util.List;

/**
 * Created by pawel on 07.12.15.
 */
@Service
public class UserService {

    private String asyncMsg = "in progress";

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void save(User u) {
        userRepository.save(u);
    }

//    @Async // a co to takie fajne?
//    public void doAsync() {
//        System.out.println("start async");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        asyncMsg = "done";
//        System.out.println("finish async");
//    }

//    public String getAsyncMsg() {
//        return asyncMsg;
//    }
}
