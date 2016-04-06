package pwr.po.webcrawler.configuration;

import com.googlecode.flyway.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pwr.po.webcrawler.service.auth.TokenAuthenticationService;

@Configuration
@ComponentScan(basePackages = "pwr.po.webcrawler", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION))
@EnableWebMvc
@EnableAsync
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").exposedHeaders(TokenAuthenticationService.AUTH_HEADER_NAME);
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.clean();
        flyway.init();
        //flyway.setInitOnMigrate(true);
        //flyway.setSchemas("SBA_DIALOG");
        //flyway.setLocations("filesystem:src/main/resources/db/migration");
        //flyway.setDataSource("jdbc:postgresql://localhost:5432", "sa", null);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-228-246-206.eu-west-1.compute.amazonaws.com:5432/dabau342amt24s?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        dataSource.setUsername("jnheqfbnlmxjmy");
        dataSource.setPassword("AERYkjjbHNvIP_9ERFd7XSrMZj");
        flyway.setDataSource(dataSource);
        //flyway.setLocations("info.novatec.eap.persistence.migration", "filesystem:src/main/resources/migrations");
        flyway.migrate();
        return flyway;
    }
}
