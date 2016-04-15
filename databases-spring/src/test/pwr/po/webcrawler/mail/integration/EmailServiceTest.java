package pwr.po.webcrawler.mail.integration;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.common.internal.PowerMockJUnitRunnerDelegate;
import org.powermock.modules.junit4.internal.impl.PowerMockJUnit49RunnerDelegateImpl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pwr.po.webcrawler.mail.integration.EmailService.SMTP;


@RunWith(MockitoJUnitRunner.class)
//@RunWith(PowerMockRunner.class)
//@RunWith(PowerMockJUnit49RunnerDelegateImpl.class)
//@PrepareForTest({EmailService.class, MailSessionFactory.class})
public class EmailServiceTest {



    @Mock
    private Transport transport;
    @Mock
    private MailSessionFactory sessionFactory;
    @Mock
    private Message message;
    @Mock
    private Session session;
    @InjectMocks
    private EmailService emailService;
    @Before
    public void setUp() throws Exception {

        //Mockito.when(sessionFactory.create()).thenReturn(session);
        //Mockito.when(session.getTransport(SMTP)).thenReturn(transport);
    }
    @Test
    public void shouldSendEmail() throws Exception {
        //emailService.sendMail(new String[]{}, "", "");

        //verify(sessionFactory).create();
        //verify(session).getTransport(SMTP);
       // verify(transport).connect();
      //  verify(transport).sendMessage(message, message.getAllRecipients());
        //verify(transport).close();

    }
}