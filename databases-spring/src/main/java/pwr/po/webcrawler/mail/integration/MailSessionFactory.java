package pwr.po.webcrawler.mail.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.configuration.ConfigService;
import javax.mail.Session;


@Service
public class MailSessionFactory {
    @Autowired
    private ConfigService config;
    public Session create(){
       return Session.getDefaultInstance(System.getProperties());
    }
}
