package pwr.po.webcrawler.mail.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.configuration.ConfigService;
import pwr.po.webcrawler.configuration.Keys;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public static final String SMTP = "smtp";
    @Autowired
    private MailSessionFactory sessionFactory;

        private static String USER_NAME ;
        private static String PASSWORD ;
        private static Properties PROPERTIES;
        private static String HOST;

        @Autowired
        private ConfigService config;
        @PostConstruct
        private void initialization(){
            USER_NAME=config.get(Keys.MAIL_user_name);
            PASSWORD=config.get(Keys.MAIL_password);
            PROPERTIES = System.getProperties();
            HOST = config.get(Keys.MAIL_host);
            PROPERTIES.put("mail.smtp.starttls.enable", config.getAsBoolean(Keys.MAIL_starttls));
            PROPERTIES.put("mail.smtp.host", HOST);
            PROPERTIES.put("mail.smtp.user", USER_NAME);
            PROPERTIES.put("mail.smtp.password", PASSWORD);
            PROPERTIES.put("mail.smtp.port", config.get(Keys.MAIL_port));
            PROPERTIES.put("mail.smtp.auth", config.getAsBoolean(Keys.MAIL_smtp_auth));
        }


        public void sendMail(String[] recipients, String subject, String body) {
            if (config.getAsBoolean("EMAILS_ENABLED")) {
                Session session = sessionFactory.create();
                MimeMessage message = new MimeMessage(session);

                try {
                    message.setFrom(new InternetAddress(USER_NAME));
                    InternetAddress[] toAddress = new InternetAddress[recipients.length];

                    for (int i = 0; i < recipients.length; i++) {
                        toAddress[i] = new InternetAddress(recipients[i]);
                    }

                    for (int i = 0; i < toAddress.length; i++) {
                        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
                    }

                    message.setSubject(subject);
                    message.setText(body);
                    Transport transport = session.getTransport(SMTP);
                    transport.connect(HOST, USER_NAME, PASSWORD);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                } catch (MessagingException me) {
                    me.printStackTrace();
                }
            }
        }
}
