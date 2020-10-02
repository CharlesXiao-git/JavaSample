package com.java.uploadfile.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * mapping to view
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	
    	registry.addViewController("/").setViewName("home");
    	registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/file").setViewName("file");
        registry.addViewController("/angular").setViewName("webapp/index");
        
    }
}
