package com.nosorio.xmentor.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/recommendations", "/assets/upload")
                            .authenticated()
                        .antMatchers(HttpMethod.POST, "/courses/{id}/enroll", "/logout", "/courses")
                            .authenticated()
                        .antMatchers("/users/**")
                            .authenticated()
                    .anyRequest()
                        .permitAll()
                .and()
                    .oauth2ResourceServer()
                        .jwt()
                .and()
                    .and()
                        .csrf()
                            .disable();
    }

    // https://github.com/spring-projects/spring-security/issues/8369
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new UnsupportedOperationException("unsupported");
        };
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
        return super.authenticationManagerBean();
    }
}
