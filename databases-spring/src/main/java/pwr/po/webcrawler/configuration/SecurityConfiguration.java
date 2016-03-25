package pwr.po.webcrawler.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import pwr.po.webcrawler.security.StatelessAuthenticationFilter;
import pwr.po.webcrawler.security.StatelessLoginFilter;

/**
 * Created by Rafal on 2016-03-22.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StatelessAuthenticationFilter statelessAuthenticationFilter;

    @Autowired
    private StatelessLoginFilter statelessLoginFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .addFilterBefore(statelessLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(statelessAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedPage("/auth/forbidden")
                .and().csrf().disable();

//                .authorizeRequests()
//                .antMatchers("/user/**").authenticated()
//                .anyRequest().permitAll().and()
//                .csrf().disable();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(this.userDetailsService)
//                .passwordEncoder(User.PASSWORD_ENCODER);
        auth .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}