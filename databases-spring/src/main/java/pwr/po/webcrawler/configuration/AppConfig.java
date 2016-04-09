package pwr.po.webcrawler.configuration;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pwr.po.webcrawler.service.auth.TokenAuthenticationService;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "pwr.po.webcrawler", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION))
@EnableWebMvc
@EnableAsync
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").exposedHeaders(TokenAuthenticationService.AUTH_HEADER_NAME);
    }

    @Autowired
    DataSource dataSource;


    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        flyway.init();
        flyway.setSchemas("dabau342amt24s");

        flyway.migrate();
        return flyway;
    }
}
