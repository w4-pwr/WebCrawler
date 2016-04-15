package pwr.po.webcrawler.mail.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.configuration.ConfigService;
import pwr.po.webcrawler.configuration.Keys;

import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

@Service
public class MailSessionFactory {
    @Autowired
    private ConfigService config;
    private Properties PROPERTIES;
    public Session create(){
        PROPERTIES = new Properties(System.getProperties());
        PROPERTIES.put("mail.smtp.starttls.enable", config.getAsBoolean(Keys.MAIL_starttls));
        PROPERTIES.put("mail.smtp.host", config.get(Keys.MAIL_host));
        PROPERTIES.put("mail.smtp.user", config.get(Keys.MAIL_user_name));
        PROPERTIES.put("mail.smtp.password", config.get(Keys.MAIL_password));
        PROPERTIES.put("mail.smtp.port", config.get(Keys.MAIL_port));
        PROPERTIES.put("mail.smtp.auth", config.getAsBoolean(Keys.MAIL_smtp_auth));
       return Session.getDefaultInstance(PROPERTIES);
    }
}
