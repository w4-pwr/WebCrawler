package pwr.po.webcrawler.configuration;

import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import javax.xml.ws.ServiceMode;


@Service
public class ConfigService {

    @Autowired
    private Environment environment;

    public String get(String key) {
        return environment.getProperty(key);
    }

    public boolean getAsBoolean(String key){
        return Boolean.parseBoolean(get(key));
    }
}
