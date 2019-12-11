package com.foxtel.spring.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * Basic authorisation with HTML form
         */
        // http
        // .authorizeRequests()
        // .antMatchers("/", "/flights").permitAll()
        // .anyRequest().authenticated()
        // .and()
        // .formLogin()
        // .permitAll();

        /**
         * Basic authorisation using auth headers
         */
        http
            .authorizeRequests()
                .antMatchers("/", "/flights").permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }
}