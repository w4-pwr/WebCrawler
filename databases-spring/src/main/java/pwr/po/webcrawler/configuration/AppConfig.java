package pwr.po.webcrawler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.*;
import pwr.po.webcrawler.service.auth.TokenAuthenticationService;

@Configuration
@ComponentScan(basePackages = "pwr.po.webcrawler")
@EnableWebMvc
@EnableAsync
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("AddCorsMapping");
        registry.addMapping("/**").allowedMethods("POST", "PUT", "GET", "OPTIONS", "DELETE").exposedHeaders(TokenAuthenticationService.AUTH_HEADER_NAME);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
