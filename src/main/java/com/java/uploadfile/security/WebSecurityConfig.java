package com.java.uploadfile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//Disable csrf, so the post request can access the service
    	http.csrf().disable();
    	/**
    	 * configure the permission to the request.
    	 */
        http
            .authorizeRequests()
                .antMatchers("/", "/file","/home","/upload","/mutifile","/getBucketNumber","/angular","/postTest").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//using username user and password password, can log in the system.
    	auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

}